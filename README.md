# API Java

REST Assured checks against Restful Booker. Java 21, Maven, JUnit 5.

## Covered

- Auth: valid credentials return a token
- Auth failure: wrong password returns no token and reason "Bad credentials"
- Booking create: asserts amount (totalprice) and that a repeated identical call returns a new id

## Domain note

The create check treats totalprice as the amount. Currency is not a field on this public API. A second identical POST produces a new bookingid — the API does not enforce idempotency. On a real payment endpoint the same payload with the same key must return the original id or be rejected so the charge is not applied twice.


A negative totalprice is also exercised. The public API either accepts it or returns 400; a payment endpoint must reject a negative amount.