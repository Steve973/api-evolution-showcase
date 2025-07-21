# Are You Building Java APIs Incorrectly? (Hint: Probably. I was!)

## Introduction

As software developers, we are all very familiar with the function and purpose
of an application programming interface (API).  We create them for different
reasons, including project organization, or communication of operations to
external actors, among other things.  Ideally, we want to write really solid
APIs with clean separation from the implementation to facilitate things like
testing, clarity, expectation, and stability across versions.  It sounds simple,
but there are subtle complexities that many developers may not fully recognize.

This article will explore **user-facing APIs** in Java libraries. We are not
talking about the `api` package that you include for organizational purposes in
your Spring Boot application, for example.  While the information in this
article may still apply to that use case, we will focus on the efforts of
producing a clean and proper API for end-user dependencies.

We will explore this by walking through the evolution of a project. We will
start at the early exploratory phase, then examine how and when interfaces
emerge, how the project might be organized around them, and how each approach
attempts to solve problems. Along the way, we will also look at the trade-offs
that each design introduces, and how those friction points can be mitigated, or
even avoided.

## Getting Started

In our fictitious assignment, we must create a library that greets our esteemed
user.  While this seems nearly impossible, I am quite sure that we will succeed
if we stay focused.  To get a feel for the task, we can vet our ideas by
writing classes to see what shakes out.  At this point, we do not need to be
rigid and formal.  Instead, we can operate in "creative mode".  This is where we
create what is known as "The Blob".  It consists of a bunch of concrete classes
that are all collocated in a single package.  For reference, please see:

[`00-the-blob`](https://github.com/Steve973/api-evolution-showcase/tree/main/00-the-blob)

Clearly, there is no API here, since the classes are all concrete
implementations.  We can see a few benefits of this as our initial approach:
- Zero overhead
- Easy to iterate
- Encourages discovery and exploration

The drawbacks are readily apparent, as well:
- No separation of concerns
- Difficult to test or reuse in isolation
- No path to modularity or pluggability
- All modifications are manual, literal, and must be stitched in explicitly

This approach is not objectively wrong. In fact, it can be an important phase in
a project where developers want to dive in and flesh out their ideas.  It is
very unlikely that anyone would ship a project that is built in this manner and,
if this is left unchecked for any length of time, it turns into a tightly coupled
monolith that resists change and assures hair loss.

At this point, since we can see our project taking shape, we can do much better,
and with minimal effort, by extracting interfaces and making this look much more
reasonable.

## The API: First Attempts

Our exploration phase has revealed where we can begin to establish an API. We
have a `GreeterService` and a `MediumUser` that is provided by a
`MediumUserService`. From this, we can extract interfaces: a `Greeter`, and a
`MediumUserService` that returns a `MediumUser`. These will form our initial
API surface.

There are two basic ways in which we might structure this code.

The first approach involves splitting our "blob" into `api` and `impl`
**packages** within a single project. This is a common pattern and often feels
like a good first step toward modularity. You can examine that here:

[`01-faux-api`](https://github.com/Steve973/api-evolution-showcase/tree/main/01-faux-api)

The second approach goes a step further and moves the `api` and `impl` into
separate **modules**, where each has its own artifact. That version can be found
here:

[`02-the-superficial-split`](https://github.com/Steve973/api-evolution-showcase/tree/main/02-the-superficial-split)

As you have likely noticed, these two approaches look fairly different at a
glance, but they are functionally identical. The method of separation, whether
by package or by module, is mostly a matter of personal preference at this
stage.

When we compare either of these versions to their "blob" predecessor, the
benefits are immediately noticeable:
- Better organization
- Matches common developer expectations
- Easier to extend or refactor later

Unfortunately, we can also recognize some remaining limitations:
- Concerns are not truly separated
- Interfaces exist, but their implementations are hardwired
- No support for runtime pluggability or replacement
- All wiring is still manual and centralized

To address these limitations, developers might use a dependency injection (DI)
framework, like Spring Boot, or Google Guice.  These can reduce hard-wiring
and enable more flexible, configurable assembly of an application. While
effective, and especially in larger applications, this approach is often less
suitable for libraries, where adding a dependency (particularly a heavyweight
one) may be undesirable.

Furthermore, this does not address another fundamental limitation: these
structures are **not pluggable**. Implementations still need to be known at
compile time, and substitution requires changes to core wiring logic.

If we want true modularity and testability, we need to go beyond structure
alone. The next step is to move the wiring logic out of the application and
into something more flexible.

## Adding Flexibility: Service Factories

To break free from manual wiring and hardcoded implementations, we can
introduce a small layer of indirection: service factories. These are dedicated
classes that are responsible for creating instances of our interfaces, based on
logic that lives outside of the application entry point.

Rather than directly instantiating `ConsoleGreeterService` or
`LoggingGreeterService` in `GreeterApplication`, we can now delegate that
decision to a `GreeterFactory` or similar abstraction. This allows the
application to request a service without knowing or caring how that service is
constructed.

You can explore this version here:

[`03-service-factories`](https://github.com/Steve973/api-evolution-showcase/tree/main/03-service-factories)

This structure introduces several improvements:
- Centralized construction logic
- Greater flexibility for runtime configuration
- Improved testability through injection of alternate factories
- Reduced coupling between application code and specific implementations

However, this approach also presents limitations:
- The list of implementations is still hardcoded within the factories
- Adding a new provider still requires changes to the factory logic
- There is no dynamic discovery or external contribution mechanism

This model is a useful halfway point. It represents a meaningful improvement in
structure and testability, but it still falls short of being truly modular or
extensible. To reach that next level, we must externalize implementation
discovery entirely.  Fortunately, the JDK has exactly what we need to achieve
this!

## The Service Provider Interface (SPI) to the Rescue!

At this point, we have centralized construction logic, but we are still
responsible for listing, selecting, and wiring every implementation
manually. If we want to allow **external modules** to provide implementations
without modifying the core of our application or library, we need a
**discovery mechanism**.

This is where Java's **Service Provider Interface (SPI)** mechanism comes into
play. By using `ServiceLoader`, we can load service implementations at runtime
based on what is available on the classpath. This allows new functionality to
be introduced simply by adding a JAR file that registers a provider. No code
changes are required to enable the new behavior.

You can examine a basic version of this architecture here:

[`04-api-spi-separation`](https://github.com/Steve973/api-evolution-showcase/tree/main/04-api-spi-separation)

This version introduces a proper SPI:
- `GreeterProvider` and `UserProvider` interfaces are declared in the `spi` module
- Implementations live in a separate `services` module
- Providers are discovered using `ServiceLoader`, based on declarations in
  `META-INF/services`
- The `core` module acts as the glue, loading the providers and using them
  to create `Greeter` and `MediumUser` instances

This version is **technically correct** and delivers true runtime pluggability.
However, the **modular structure introduces some friction**.

The separation between the `api` and `spi` modules creates awkward boundaries.
The API and SPI often share model types, such as `MediumUser`, and must depend
on one another to function. In real-world scenarios, SPI implementations will
almost always need to depend on the API, which means the two modules are
tightly bound even if they are physically separated.

This separation introduces packaging complexity without offering much
practical benefit. In fact, it often leads to more confusion than clarity.

To simplify things, we can keep the separation of **concerns** without the
separation of **artifacts**. That is the next and final evolution.

## The Final Evolution: Reduction of Unnecessary Separation

At this point, we have achieved real pluggability through the Service Provider
Interface mechanism. Implementations can be discovered at runtime, and new
features can be introduced without altering any core wiring. However, there is
still one significant structural concern that remains: **unnecessary
modularization**.

In the previous version, we separated the `api` and `spi` modules to clarify
their respective roles.  One is intended for consumers, and the other one is for
service implementers.  While this seems reasonable in theory, it creates awkward
friction in practice. The SPI is a contract, just like the API. They often share
the same models, and implementations of the SPI will always depend on the API.

The separation creates the illusion of a boundary where none is needed. It adds
overhead without delivering value.

You can examine the improved version here:

[`05-a-real-solution`](https://github.com/Steve973/api-evolution-showcase/tree/main/05-a-real-solution)

In this final version:

- API and SPI interfaces are housed in a single `api` module
  - SPI types live in a dedicated `spi` package to indicate audience and intent
  - There is no need to manage dependency alignment between separate artifacts
- The `core` module handles instantiation using `ServiceLoader`, just as before
- The `services` module provides actual implementations and registers them via
  `META-INF/services`

This structure retains the power of the SPI, but without the packaging overhead.
It is **clean**, **cohesive**, and **fully extensible**.  By collapsing the SPI
into the API, we clarify intent: both sets of interfaces are stable contracts.
They simply serve different purposes.

If you truly feel the need for separation, consider dividing your codebase by
**functional concern** rather than interface role. If, for example, your API
supports both analytics and persistence, you might define separate artifacts
for each, keeping their respective SPI types co-located. This provides real
value through modularity without artificial barriers.

This is the structure that most closely aligns with what many developers
intuitively want: a library that can be extended cleanly, understood easily, and
used confidently, and all without an explosion of artifacts or indirection.

## Some Final Thoughts

If you have read this far, please accept my sincere thanks. I hope that you
found something in this article that justified your time and attention.

I will not claim there is a single “best” way to design a Java API. Context,
constraints, and goals all influence what the right choice looks like. My goal
here was not to prescribe a universal solution, but to illuminate a topic that
is often overlooked, despite its impact on maintainability, flexibility, and
developer experience.  The most important thing is to choose the strategy, and
structure, that provides the best fit for your use case, while being aware of
the cost/benefit tradeoffs.

Good API design involves more than surface-level structure. The choices we make
about organization, wiring, and pluggability shape how others use and extend
our code. If this article helped you reflect on those decisions with new clarity,
then it has done its job.

Thank you for reading.  If you have some experience or ideas about this topic,
please share it in the comments section.  If you believe that I have omitted
anything important, or if I have explained anything incorrectly, I would really
value your feedback.
