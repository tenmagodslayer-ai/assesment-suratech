# #inc-0907 — excerpt

> Times in UTC. Participants are identified by role.

| Time | Who | Message |
|---|---|---|
| 15:41 | Contact center lead (CO) | Customers in Colombia cannot get quotes since about 15:35. They see "try again later". |
| 15:46 | On-call engineer (channel) | `quote-xapi` dashboards show errors in CO, CL and MX. CPU of the replicas is at 38%, memory is fine. |
| 15:52 | On-call engineer (channel) | Declaring SEV3. Colombia looks like the most affected country from the contact center reports. |
| 16:02 | On-call engineer (channel) | `pg-channel-co` CPU is at 88%. Could be the database. @DBA can you check? |
| 16:06 | Identity team | Key rotation done at 16:00. A few 401s while caches refreshed the keys; they stopped at 16:05. |
| 16:09 | DBA | The monthly funnel report has been running since 15:00, as every month-end. It finished at 15:58. Queries from `channel-bff-co` look normal, under 50 ms. |
| 16:11 | On-call engineer (channel) | Errors continue after the database CPU went back to normal. |
| 16:14 | Nimbus Digital | 3.8.0 only adds resilience (retries), so it should help, not hurt. Mexico has 3× the normal traffic; it is month-end. |
| 16:18 | Channel manager (acting as incident commander) | Let's give `quote-xapi` more capacity. Scaling from 2 to 4 replicas. |
| 16:31 | On-call engineer (channel) | Colombia and Chile improved after the scale-out. |
| 16:43 | On-call engineer (channel) | Errors are back in CO and CL and Mexico is worse: `quote-papi-mx` p95 above 45 s. |
| 16:55 | Payments Integration Technical Lead | MX org at 85% of its daily API allocation. Pausing the Mexican payment consumers per our runbook. Who is consuming the MX org? |
| 16:58 | Salesforce platform team | Channel integration user in MX: about 856,000 calls since 00:00 UTC (budget 250,000 per 24 h) and about 90 requests/s in the last hour. Mostly queries on `Rating_Table__c`. |
| 17:00 | Channel manager (acting as incident commander) | Decision: roll back `quote-papi-mx` to 3.7.2. @Nimbus please proceed. |
| 17:05 | Nimbus Digital | 3.7.2 deployed. |
| 17:20 | On-call engineer (channel) | Latency and error rates back to normal in the three countries. Mitigated. |
| 17:30 | Payments Integration Technical Lead | Requested a temporary allocation increase for the MX org to resume payments. |
| 17:45 | On-call engineer (channel) | `quote-xapi` back to 2 replicas. |
| 18:20 | Salesforce platform team | Temporary increase of 500,000 calls granted until 06:00 UTC. |
| 18:20 | Payments Integration Technical Lead | Consumers resumed. |
| 18:20 | Channel manager (acting as incident commander) | Closing the incident. Postmortem to follow. |
