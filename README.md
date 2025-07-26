# API/SPI Evolution – From Day One to Nearly Perfect

This project demonstrates the evolution of library/module design in Java by
focusing on how developers *think* they’re building a clean API, and how they
can actually get there by embracing proper SPI-based architecture.

## Overview

The project contains multiple subprojects, each representing a common stage in
the journey toward a well-factored, pluggable Java library. The modules
intentionally evolve toward a model that separates API, SPI, implementation, and
wiring logic into distinct, purpose-driven artifacts.

---

## The Published Medium Article

What follows is some information about the ideas that went into the published
article, but if you want to skip all of that, then you can find the article that
I published to Medium
[here](https://medium.com/@steve973/are-you-building-java-apis-incorrectly-hint-probably-i-was-3f46fd108752). The article includes links to each project module and provides a
more engaging narrative version of what is discussed below.

---

## Project Modules

**Obligatory Disclaimer**: There is no "objectively best" approach or structure
to follow when you design your API. The best approach is whatever you (and/or
your team) decide to implement, given your requirements, goals, preferences, and
any other factor that constitutes an "informed decision". The goal of this
project and article is not to *prescribe*, but to contribute some potentially
overlooked details to your evaluative criteria.

### 00-the-blob

> _"All code lives together in harmony. Until it doesn't."_

The initial module reflects how many developers start: by mixing the API, the
implementation, and usage into a single project. Interfaces might exist, but
they're co-located with implementation details, making separation of concerns
(and reuse) nearly impossible.

This is where you get:
- Circular dependencies
- Implicit contracts
- No isolation
- No pluggability

#### Project Structure

This module contains a single monolith project:

- **All code in one place**  
  Everything — models, interfaces, implementations, and even utilities — is
  lumped together in a single source set. There’s no module separation or
  visibility control.

- **No modular boundaries**  
  Users, services, and infrastructure logic are mixed together, making the code
  difficult to test or extend.

- **Tight coupling everywhere**  
  Every part of the codebase can reach every other part, encouraging poor
  separation of concerns.

This layout is dead simple to get started with, but turns into technical debt
almost immediately once you try to grow or generalize it.

---

### 01-the-faux-api

> _"We made a package called `api`, so it's modular now, right?"_

Here, the code has been split into "api" and "impl" *packages* within the same
project or artifact. While this *looks* like progress, it’s still tightly
coupled. Implementations bleed into the usage layer, and there's no mechanism to
cleanly plug in different implementations. This project shows that package
separation is only superficial separation, and it does not solve the problem of
creating a clean and maintainable API, or the benefits that it brings. In short,
it improves *perceived* structure but fails to deliver actual separation.

#### Project Structure

Still a single project, but with *package-based* separation:

- **Single artifact**  
  The code is not split into separate subprojects — it all compiles and builds
  into one artifact.

- **`api` and `impl` packages**  
  There’s a superficial separation inside the code via package names, with
  interfaces in something like `org.foo.api` and implementations in
  `org.foo.impl`.

- **Still tightly coupled**  
  The packages may suggest separation, but there’s no enforcement. All classes
  can access each other, and users of the library have full access to internals.

This is a common early step developers take toward structure — but it offers no
real encapsulation or modularity.

---

### 02-the-superficial-split

> _"We split the API into its own artifact. But now everything depends on
> everything else, anyway."_

This module extracts the `api` into a separate subproject, but it’s still just a
facade. The implementation has to reach back into the `api`, and the users have
to depend on both to get anything done.

We start to see how an API without a proper lookup mechanism becomes tightly
entangled with the impl layer.

#### Project Structure

Now split into two subprojects:

- **`api` module**  
  Contains public-facing interfaces and data types intended to represent the
  external contract of the library.  
  However:
  - No SPI yet
  - No provider mechanism
  - No enforcement of separation between consumers and implementation

- **`core` module**  
  Contains the actual implementation logic that relies on the API types.  
  It:
  - Implements the interfaces
  - May expose additional internal classes not meant for external use
  - Requires consumers to depend on *both* `api` and `core` to be useful

This project begins to enforce modular boundaries but still fails to decouple
implementation from usage — clients must pull in everything just to get anything
done.

---

### 03-service-factories

> _"We added factories. Now it's abstract... right?"_

At this stage, the project introduces **factory classes** as a way to abstract
instantiation logic. Instead of directly calling constructors or hardcoding
decisions in `GreeterApplication`, we wrap that logic in separate components
like `StringSpecifiedGreeterFactory`.

This is a common evolution point: developers start to realize that *how* a
service is created should be abstracted from *where* it's used. Factories offer
a procedural mechanism for injecting runtime behavior without fully committing
to pluggability yet.

#### Project Structure

Now includes factory logic:

- **`api` module**  
  Continues to define public-facing contracts (`Greeter`, `MediumUser`,
  `MediumUserService`).  
  No SPI yet—factories are still implementation-specific and not discovered
  dynamically.

- **`core` module**  
  Introduces service factories.  
  Implements wiring logic using `if`/`switch`-based instantiation inside factory
  classes.  
  Implementation classes live alongside factories.  
  Usage logic calls into the factories instead of hardcoding implementation
  types.

This structure improves runtime flexibility, testability, and the ability to
centralize wiring logic—but it’s still limited by its reliance on **manual
enumeration of known implementations**. Nothing is pluggable. There’s no
discovery mechanism. Adding a new implementation still requires modifying the
core logic.

This is a useful halfway step—but it isn’t yet a modular or truly extensible
solution.

---

### 04-api-spi-separation

> _"We split SPI into its own module. It works—but was that actually helpful?"_

This stage introduces a **proper SPI mechanism** using `ServiceLoader`, and
everything technically works: providers are pluggable, service discovery is
dynamic, and runtime wiring is no longer manual.

However, this design also separates SPI into its own dedicated module,
independent of the API module. At first glance, this seems like a clean,
layered architecture. After all, API is for consumers, and SPI is for
implementers.

But this separation introduces **new friction**:

- The API and SPI often share model types (like `MediumUser`)
- SPI implementations depend on both API and SPI artifacts
- The core module depends on both just to glue things together
- Consumers might inadvertently pull in SPI transitively
- The separation adds packaging complexity with no practical benefit

This structure is *functionally correct*, but **structurally awkward**. SPI
interfaces are still part of the API surface—they're just for a different
audience. Keeping them in a separate artifact creates more confusion than
clarity.

#### Project Structure

- **`api` module**  
  Defines consumer-facing types: `Greeter`, `MediumUser`, etc.

- **`spi` module**  
  Defines provider-facing contracts like `GreeterProvider` and
  `UserProvider`.

- **`core` module**  
  Loads providers via `ServiceLoader`, invokes SPI implementations to satisfy
  API contracts.  
  Contains no implementation logic directly.

- **`services` module**  
  Registers SPI implementations and provides default behavior.  
  Adds provider declarations under `META-INF/services`.

This is a fully functional pluggable architecture, but one that introduces
modular separation without gaining meaningful modular benefits. In the next
stage, we collapse the API and SPI into a single module, and the result is
simpler, clearer, and just as extensible.

---

### 05-a-real-solution

> _"A clean API. A discoverable SPI. One module. One intent. No nonsense."_

This final stage resolves the friction from the previous iteration by **co-locating
API and SPI in a single module**. Rather than separating contracts based on
audience (consumer vs. implementer), we treat both as part of the **public surface
area**, which is a set of stable contracts that serve different roles.

By putting `Greeter`, `MediumUser`, and their associated providers in the same
artifact, we avoid modular overhead while improving cohesion, discoverability,
and extensibility.

#### Project Structure

- **`api` module**  
  Contains both API and SPI interfaces:
  - Public-facing types (`Greeter`, `MediumUser`, etc.)
  - Service Provider Interfaces (`GreeterProvider`, `UserProvider`)
  - A `spi` package makes implementer-facing types clearly distinct

- **`core` module**  
  - Provides runtime wiring via `ServiceLoader`.  
  - Offers simple entry points for users to get working service instances
    without manual configuration.

- **`services` module**  
  - Contains actual implementations of the SPI:
    - `ConsoleGreeterProvider`, `LoggingGreeterProvider` for two types of
      greeters
    - `ArbitraryMediumUserProvider` to get the user that contains the name
      used in the greeting
  - Registers them in `META-INF/services` so that
    discovery is automatic.

#### Why This Works

This layout is:
- **Simple**: No unnecessary artifact boundaries
- **Flexible**: Drop-in implementations via the SPI
- **Clear**: Contracts are visible, discoverable, and intentional

By grouping the API and SPI in one place, this design avoids the illusion of
separation and delivers **real pluggability with minimal ceremony**.

This is the version you'll wish you'd started with.

---

## SPI Design Philosophy

- `spi` lives **alongside** `api`, as a **sibling package** inside the API module.
    - Why? Because both are **contracts** — one for consumers, one for implementers.
    - Keeping them together makes the boundary visible and cohesive.

- The **core module** owns the instantiation logic via factories or static methods.
    - It depends on `api` (for the SPI), but **not** on any implementation.
    - It's where users go to "get stuff done" without thinking about SPI wiring.

- The **services module** depends on the `api` and implements the SPI.
    - Drop this on the classpath, and everything just works.

---

## Goals of This Project

- Show the **pain** of common architectural patterns (and how they fail).
- Provide **clear progression** toward a highly modular and testable design.
- Enable readers to **recognize smells** in their own codebases.
- Deliver a simple, but rare and powerful example of true API/SPI decoupling.

---

## Usage in Medium Article

This repo is meant to be referenced in a Medium article titled something like:

> “**Are You Doing Java APIs Incorrectly? (Hint: Probably. I was!)**”

You’ll see highlighted code snippets, architectural comparisons, and inline
links to each of these projects. Feel free to browse the code, follow along,
and copy what works.

---

## Contributing

This isn’t an open-source library; it’s a teaching tool. But if you have any
thoughts, corrections, comments, or examples to add to this effort, please feel
free to contact me, and share them. I am always looking for better ways to
arrive at clarity, and to share my ideas with others.
