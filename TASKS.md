# Tasks

Read `SCENARIO.md` first. Suggested times are a guide. Put each deliverable exactly where indicated: reviewers look for it there. Write diagrams in **Mermaid** inside Markdown so they can be read as text. Be concise: we value clarity over length, and a table over long prose when it fits.

When you rely on a fact from the material, say where it comes from (for example "C2", or "`SALESFORCE_CONSTRAINTS.md`"). When you need a fact that is not given, record your assumption in `ASSUMPTIONS.md`.

---

## Part A — Architecture, standards and governance (≈ 2 h · 25%)

Design the **target architecture** of the Direct Digital Channel that will carry Home Essentials (BR-01) in the three countries and, over time, the existing journeys.

**1. `docs/architecture/ARCHITECTURE.md`**
- A **context diagram** (C4 level 1) and a **container diagram** (C4 level 2) of the target state, showing trust boundaries.
- A table that allocates each capability to **regional** (built once) or **country-specific**, and explains how country variations are handled without forks (C3).
- How the channel reaches Salesforce through the API-led layers, and how you meet C2 and C6 at month-end peaks given that each org is available ≈ 97% of the time.
- How a failure or overload in one country is contained (C1).
- Identity and data protection: how the customer and the country are established for each request, for customers and for advisors, and how C5 is met.
- The transition from the current state: what moves first, and how you avoid a big-bang migration.
- **Guidelines and standards** for the teams that build on this architecture, internal and Nimbus: API design (naming, versioning, error model), country configuration, resilience defaults, and the rules for reuse across countries. Include only what you would actually enforce.

**2. `docs/api/quote-experience-api.yaml`**
- An **OpenAPI 3.x** contract for the regional Experience API that the channel uses to quote Home Essentials: at least *create a quote* and *retrieve a quote*.
- Show how it handles the country, versioning, errors, security and the rating-table version shown to the customer. It must be valid OpenAPI; it does not need to be exhaustive.

**3. Two ADRs in `docs/adr/`**, using [`ADR-0000-template.md`](docs/adr/ADR-0000-template.md):
- **`ADR-0001-regional-reuse-model.md`** — how the channel is built once and served to three countries. For example: one regional deployment with country configuration, one deployment per country from a shared codebase, or another model. Consider C1, C3, C5, C8 and C9.
- **`ADR-0002-channel-to-core-integration.md`** — how quoting and issuance reach Salesforce. For example: through the Experience, Process and System APIs; caching of rating tables, and where; direct access under an exception; or another option. Consider C2, C4, C6 and Salesforce availability.
- Each ADR compares at least two real options and states the consequences, including what becomes harder.

**4. `docs/architecture/INTERFACES_AND_GOVERNANCE.md`**
- A **dependency map** between the Direct Digital Channel and the other cells and teams for BR-01: who provides what, through which interface, and which agreements are needed (contracts, SLOs, API budgets).
- How technical decisions that affect several cells should be made and recorded with the other Technical Leads.
- How the channel's Salesforce API consumption is planned, measured and agreed with the other consumers of each org.

---

## Part B — Quality and automation (≈ 1 h · 15%)

Baseline: [`context/DELIVERY_METRICS.md`](context/DELIVERY_METRICS.md).

**1. `docs/engineering/ENGINEERING_STANDARDS.md`**
- **Test strategy** for the cell: which kinds of tests, at which level (frontends, BFF, Experience and Process APIs), and who writes and runs them. Include contract tests between consumers and providers, and the non-functional tests that C1 and C2 require.
- **Quality gates**: each gate with a measurable threshold, the stage where it is enforced, and what happens when it fails. Include security gates.
- **Code review and branching policy**, including changes written with AI assistance.
- How the standards apply to **Nimbus Digital** (C8): what you would require in the contract renewal and how you would verify it.
- **Delivery targets** for the end of Q2 2027, based on the DORA metrics of the baseline, with your reasoning.
- A **golden path** for creating a new service or API in the cell: what the template includes so that teams start compliant.

**2. `docs/engineering/pipeline-template.yml`**
- A reusable pipeline template (Azure Pipelines or GitHub Actions) that a BFF or an Experience or Process API would use from commit to production in the three countries, with your gates, approvals and a progressive rollout by country.
- **Do not run it.** It must be readable and coherent; tasks may be placeholders if their purpose is clear.

---

## Part C — Reliability and operations (≈ 1 h 15 min · 20%)

Evidence: [`incident/`](incident/).

**1. `docs/operations/SLOS.md`**
- SLIs and SLOs for at least two critical user journeys of the channel (*get a quote*, and *buy and issue a policy*), per country. Explain how each SLI is measured.
- An **error budget policy**: what the cell does when a budget is at risk or exhausted.
- **Alerting**: which alerts page someone, and why.
- A **safe deployment strategy** for the cell, including the components deployed by Nimbus: how a change reaches the three countries, how it is observed, and how it is rolled back.

**2. `docs/operations/INC-0907-POSTMORTEM.md`**
- **Triage** as it should have been done: severity, impact by country, who should have been involved, and the first communication.
- **Timeline** reconstructed from the evidence.
- **Root cause and contributing factors.** Cite the evidence for each claim (file and timestamp or line). State which hypotheses you ruled out, and why.
- **5 whys**, and an analysis of the response itself: what helped and what made things worse.
- **Action items** with owner (role or team), priority, and how you would verify that each one works. Postmortems at Meridian are **blameless**.

---

## Part D — Pull request review and mentoring (≈ 1 h · 15%)

Material: [`pr-review/`](pr-review/). PR-388 was opened by a Nimbus Digital developer against `channel-bff`. Read the description in `PR-388.md` and the changes in `PR-388.diff`. The folder `pr-review/codebase/` contains the relevant files of `main` before the change, so you can see the existing patterns of the codebase.

**`docs/review/REVIEW.md`**
1. **Findings**: a table with file, line (in the new version of the file), issue, severity (Critical, High, Medium or Low) and the change you request, ordered by severity.
2. **Verdict**: approve, approve with comments, or request changes, and what must happen before the merge.
3. **Refactoring proposal**: how this feature should be structured to fit the target architecture and the standards from Part A, and in which order you would ask the vendor to get there.
4. **Feedback to the author**: the message you would post to the developer (maximum 400 words). The developer works for a third party, is competent and is under deadline pressure. Write it so that they learn the principles, not only the fixes.

---

## Part E — Roadmap and feasibility (≈ 1 h · 15%)

Material: [`context/BUSINESS_REQUESTS.md`](context/BUSINESS_REQUESTS.md), [`context/TECH_DEBT_INVENTORY.md`](context/TECH_DEBT_INVENTORY.md), [`context/TEAM.md`](context/TEAM.md), and what you learned in Part C.

**1. `docs/roadmap/ROADMAP.md`**
- Your **prioritization method**: how you score value, effort and risk, and the resulting scores for the inventory items, BR-01 and the actions from your postmortem.
- A **roadmap for Q1 and Q2 2027** that fits the capacity in `TEAM.md`, shows dependencies and milestones, and states what you will **not** do, and why.
- The main delivery risks and how you would manage them.

**2. `docs/roadmap/FEASIBILITY_GENAI.md`** — your technical feasibility analysis of BR-02:
- Value, effort and risk, with the evidence you used and the information that is missing.
- The options you considered (including not doing it now) and your recommendation.
- If you recommend exploring it: a time-boxed spike with scope, success metrics and explicit **go / no-go** criteria.
- The risks specific to a customer-facing LLM feature and the controls you would require.

**3. `docs/roadmap/MEMO_SPONSORS.md`** — one page for the sponsors of BR-01 and BR-02, in business language: what they will get and when, the trade-offs you made, and what you need from them.

---

## Part F — Responsible AI (≈ 30 min · 10%)

**1. `AI_USAGE.md`** (repository root, **mandatory**). Complete the template.

**2. `docs/ai/AI_GUIDELINES.md`** — the guidelines for the use of AI by the developers of your cell, internal and third-party, in software development (coding assistants, test generation, documentation, code review). Make them concrete enough to be followed and checked. Include:
- What data may and may not be shared with AI tools, and which tools are allowed.
- The review and validation that AI-generated output requires before merge, and how its use is disclosed.
- Accountability, security and intellectual property.
- How you would roll the guidelines out, enforce them and measure whether they work.
- The additional controls that apply when the cell **builds** features that call an LLM (you may link to your Part E analysis).

---

## Also required

- **`SUBMISSION.md`** — summary, time spent per part, the three decisions you are most confident about, the one you are least sure about, and what you would do with more time.
- **`ASSUMPTIONS.md`** — every assumption you made.
- **`SUBMISSION_CHECKLIST.md`** — tick every item before you send the link.
