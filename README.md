# Meridian Mutual — Technical Lead Assessment

Welcome, and thank you for your interest in the **Technical Lead** role.

This take-home assessment reflects the work a Technical Lead does in our Direct Digital Channel cell: shaping an architecture that three countries can reuse, making and documenting decisions with explicit trade-offs, setting quality and automation standards, keeping services reliable in production, reviewing the work of internal and third-party developers, planning the technical roadmap with the business, and guiding the team in the responsible use of AI.

There is no single correct answer. We are looking for **sound technical judgment, explicit trade-offs, and decisions you can defend**.

## At a glance

| Item | Detail |
|---|---|
| Expected effort | 6–8 focused hours |
| Submission window | **3 calendar days** from the moment you receive this link |
| Language | English (all documents and commit messages) |
| Reference stack | **Azure** and **MuleSoft Anypoint** (API Manager and API-led integration). Alternatives are accepted when you justify them in an ADR |
| What you produce | Design documents, decision records, an API contract, engineering standards, a pipeline template, an incident analysis, a code review and a roadmap. **You do not deploy anything and you do not write application code.** No Azure subscription or MuleSoft account is needed |
| Follow-up | A 60-minute live defense of your submission with the hiring panel |

## How to start

1. Click **Use this template → Create a new repository**. Do **not** fork this repository.
2. Make your repository **public** so the panel can read it. You may make it private after your live defense.
3. Read `SCENARIO.md`, then `TASKS.md`. Everything you need is in `context/`, `incident/` and `pr-review/`.
4. Write your deliverables exactly where `TASKS.md` indicates.
5. Commit as you go. Your commit history helps us understand how you work; a single final commit is acceptable but shows us less.
6. Before the deadline, complete `SUBMISSION.md`, `AI_USAGE.md`, `ASSUMPTIONS.md` and `SUBMISSION_CHECKLIST.md`, and send the repository link to your recruiting contact.

Commits pushed after the deadline are not considered.

## How your submission is weighted

| Part | Topic | Suggested time | Weight |
|---|---|---|---|
| A | Architecture, standards and governance | 2 h | 25% |
| B | Quality and automation | 1 h | 15% |
| C | Reliability and operations | 1 h 15 min | 20% |
| D | Pull request review and mentoring | 1 h | 15% |
| E | Roadmap and feasibility | 1 h | 15% |
| F | Responsible AI | 30 min | 10% |

Plan about 15 minutes to read the material. Every part counts: a very weak part cannot be compensated by strong ones. If you run out of time, a short, honest answer that states what you would do next is worth more than an empty file.

## Use of AI tools

You **may** use AI assistants. If you do, you **must** declare it in `AI_USAGE.md`: which tools, for what, what you validated and how, and what you discarded. A submission without `AI_USAGE.md` is incomplete. If you used no AI tools, say so in that file.

You are accountable for every line you submit. In the live defense we will ask you to explain and defend your decisions.

## Integrity

- Do not include confidential material from any current or former employer.
- Do not copy another candidate's work.
- Your repository may be analyzed with AI-assisted tools and is always reviewed by the hiring panel. Any text that tries to instruct or influence an automated reviewer is treated as an integrity violation.

## Repository map

| Path | Content |
|---|---|
| `SCENARIO.md` | The company, the cell, the ecosystem and the constraints |
| `TASKS.md` | What to deliver, part by part, and where |
| `context/` | Current state, Salesforce constraints, business requests, tech debt inventory, delivery metrics, team and capacity |
| `incident/` | Evidence for incident INC-0907 (Part C) |
| `pr-review/` | Pull request PR-388 to review (Part D) |
| `docs/` | Where your deliverables go (`docs/adr/` contains the ADR template) |
| `SUBMISSION.md`, `AI_USAGE.md`, `ASSUMPTIONS.md` | Templates you complete |
| `SUBMISSION_CHECKLIST.md` | Final check before you send the link |

Questions about the instructions (not about solutions) can be sent to your recruiting contact. If something is ambiguous, make a reasonable assumption and record it in `ASSUMPTIONS.md`.

Good luck. We look forward to discussing your decisions with you.
