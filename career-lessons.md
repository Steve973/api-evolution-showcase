# After Thirty Years In Dev: Lessons From a Former Code Clown

I started my software development/engineering career in 1995 while I worked at
an electronics company in Souderton, Pennsylvania. During the past thirty years,
I have had a chance to reflect on my career, and there are several things that
made huge impacts on my effectiveness in my profession. Make no mistake about
it — my experience is not more profound or unique than yours or anyone else's.
Let's see if we can all compare notes, and maybe we will find some common
themes. Who knows? Maybe we can even give some recent grads and junior engineers
a bit of a boost.

After graduating with a BS in Computer Science, I have no problem admitting that
I was _the worst_ software slinger that I knew. While I did really well in my
program at school, when I had to participate in a team environment and write
software that was worth even part of what they paid me, I was exceptionally
_terrible_! Since then, I like to think that I have improved a bit. I wonder if
your experiences line up with mine in at least a few ways. If you are up for it,
let's reflect on our careers and see if they line up at all.

## Testing: Ain't Nobody Got Time For That!

How many of you are big fans of writing developer tests? Yeah, it's not like
writing hundreds of tests gives you the satisfying payoff that you get when you
solve a hard technical problem. But it lets you know that all of your hard work
has paid off, because your code **really works** and there is objective proof!

I can remember when I was a junior member on a team that was writing a pretty
sophisticated analytic processing engine, and they were using the Spock
Framework for testing. I was new to both Groovy and Spock, and I was not exactly
thrilled to have to endure the learning curve of two things just to be able to
submit a merge request. But my coworker encouraged me to persevere, and told me
that it would be worth it. This team was pretty strict about full coverage, with
emphasis on testing the branches as well as the lines.

Like all junior developers, I found a lot of things to be really challenging to
test. It quickly became obvious to me that **my code** was the problem, and
writing all of these tests showed me how to write **testable code**. That means
that I learned to greatly simplify everything, and avoid the dreaded high
cyclomatic complexity numbers. I began to make a habit of writing small,
single-purpose methods, and to compose behavior through invoking them. I
realized that directly instantiating objects made testing way harder than it
needed to be. So I started making the instantiation strategy swappable.

I opened this section by asking how many of us are big fans of writing tests,
and that was an intentionally ridiculous question. The answer, of course, is
that probably none of us love to write tests. But isn't that the whole point? If
we learn how to write code that is easy to test, then we greatly reduce the
amount of time that is required to write them. To me, that is a huge win, and
it makes our jobs a hell of a lot easier. So, who has time for that? I would
argue that it is imperative to make time for it now to save a lot of time
later.

## What’s So Bad About State, Mutation, and Side Effects, Anyway?

Over the course of my career, I learned a lot of valuable things from my late
brother, Jeff. At one point, he was deep into a project written in the awesome
JVM language Clojure, where immutability isn’t just encouraged, it’s baked in.
Around that time, we talked a lot about coding, and his excitement for Clojure,
and the transformative mindset around immutability, kept coming up. That rubbed
off on me. I started making my Java code a lot more functional, and a lot less
imperative. This was right around the time that Java 8 came out, and my team
embraced it right away. This let me take full advantage of streams and things
like `Optional`, which opened up a more expressive style.

That shift made concurrency feel almost easy. When you’re not juggling shared
mutable state, you don’t have to worry if something changed under your feet, or
which thread did it. You can avoid semaphores or `synchronized` blocks, too, in
most places. Suddenly, my code was less cluttered and _way_ more predictable,
whether concurrency was involved or not. Best of all, you get to skip the
nightmare debugging sessions where you’re chasing some ghost of a concurrency
bug across 20 threads and a race condition.

It turns out that introducing yourself to immutability doesn’t just change your
code. It changes how you think about problems. Jeff’s love of Clojure taught me
that immutability isn’t just a style. It’s a bit of a superpower. Avoiding
state, mutation, and side effects where you don’t explicitly need them is one of
the best ways to ensure you’re writing code with clear intent.

## Don't I Become a Lot Better When I Specialize In Only A Few Areas?

Early in my career, I was just trying to keep my head above water and not look
too incompetent. I stuck to the tickets I was given and hoped I could pull them
off without too much damage and without getting skewered during code reviews. At
that point, I focused on the work I was assigned and just tried to get it done
without screwing up. I didn’t even have the base skill set yet to begin thinking
about specializing in anything.

As I gained experience, that freed me up to begin to explore other areas and
take a few more risks. I’ve always been super curious about everything. Add a
bit of ADHD to the mix, and I’d sometimes wander into tasks that weren’t
strictly development-related. I have been using Linux since 1998 or 1999, and
that allows me to occasionally handle some systems administration tasks, or to
help when things are misconfigured.

As time went on, I began to almost make a habit of jumping in wherever I saw a
gap, or where other people noticed difficulties, or avoided altogether. It
didn't even matter if I had no solid experience in the area, and sometimes,
that was even better! Remember, I have been the worst developer on the team,
and unfamiliar territory was already pretty ... familiar. Some tasks seemed dull
or tedious at first. But I usually found a way to breathe some life into them,
at least enough to make them worthwhile as a learning experience, and make the
experience better for everybody using or maintaining them. And immersing myself
in tasks that I was mostly clueless about has proven to be a great way to learn
a _lot_ of things _very quickly_.

I definitely do not want to knock specialization. Sometimes, things can really
resonate with us, and we want to dive in as deeply as possible, or center our
careers around them. I can definitely appreciate and admire that. I have a
particular interest in Enterprise Integration Patterns (EIPs) and knowledge
graphs. However, I really like being able to dive into as many areas of a system
as possible, because that helps me think in terms of _systems_, from the
10,000-foot view and down into the internals. I feel like this has shifted my
perspective, over time, more towards systems thinking.

To me, writing software involves two main aspects: concepts and tools. Jumping
into the fire, as often as possible, introduced me to a lot of both!

## With Great Power Comes Great Responsibility

Ok, ok... This section title is dramatic and dorky, and I admit that it is
designed to get a reaction, but there is a bit of truth to it, and I wonder if
you will agree. Software engineers are sometimes a cranky bunch, and true help,
collaboration, and mentorship can be hard to find. Hell, sometimes it's hard to
even _admit you don't know something_ without worrying that you're going to lose
face in front of your team. I admit that it has not always been the case, but I
have been working toward being as helpful and as non-judgemental as I can be.
This is another aspect that has been incredibly helpful to me in my career,
even if I can't explain it as clearly as I would like.

I can easily count, on one hand, the number of times in my career that I can say
I had someone who I could look to as a mentor. Most of the time, it felt like
everyone was trying to be the smartest person in the room. But those few times
when I could discuss decisions and blockers with someone who I knew would not
judge me, and just wanted to help? Those times were **pure gold** to me. When
someone would explain a different perspective, or point me in the direction of
some library that would fit a task better, it always helped with my growth and
morale on a level that was better than finding the right snippet through a
Google search.

Over time, I realized that if I could be that kind of teammate for someone else,
I would gladly do my best to help. Rather than shred somebody in a code review,
or jab them with some snark, I would instead offer them something like, "Yeah,
I've struggled with that, too, and here's what helped me." I have found that
there are so many benefits to small kindnesses like this. Better morale helps
with team cohesion and communication. A team that can communicate better will
always produce better results. The teams I’ve seen grow together, where everyone
feels ownership and success, always seem more cohesive, efficient, and proud of
what they build. Now compare that to teams with authoritarian and condescending
leadership, and you will recall how those teams constantly _struggle_ with
everything from execution, to member retention, to delivery.

This is where it feels challenging to point to specific things that I have
gained in terms of my career. I like to think of it in terms of the value that
I can add to a team in an overall sense. If I am someone that people can rely
on, and talk to about anything before it becomes an emergency, then that lowers
friction. And when friction goes down, everything gets better: the communication,
the collaboration, and eventually, the product itself. Would it be too corny for
me to call it a "value multiplier"? Well, feel free to make fun of me in the
comments!

## Ok, Steve. You Learned Some Things. So What?

I didn’t write this because I think my career is particularly special. I don’t
believe there’s anything uniquely profound about what I’ve gone through. But I do
think a lot of it will sound familiar, especially to developers who’ve been at
this for a while and still feel like they’re figuring it out. Like me.

Maybe something here sparked a thought. Maybe it gave you a new way to look at
your own experience. If nothing else, I hope it can start some conversations, or
even help someone who’s been stuck to find a new way to engage with their work.
Growth doesn’t always come from grand decisions. Sometimes it’s just about
showing up differently, even in the same job.

If any of this helped with that, then this was worth writing. And if you made it
this far, then I cannot thank you enough for choosing to spend your valuable
time by reflecting on these things with me. I hope that you will share your own
experiences in the comment section, because I would be honored to learn from you.