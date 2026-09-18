# Individual Sketch — Week 4 Round 2
**Student:** Kaashif Khan
**Date:** 9/17/2026

---

## 1. Tonight's Prompt

Working alone, against your group's docs/design/domain-model.md:

1. Draw Customer Places Order as a sequence diagram in Mermaid, working from the numbered steps of the use case you just wrote. The actor, at least three entities named exactly as your model names them, every arrow labelled with the message in plain language — askForCurrentPrice, not getPrice() — and the return path, not just the outgoing calls.

```mermaid
sequenceDiagram
    actor Customer
    participant Order
    participant MenuItem
    participant Barista

    Customer->>Order: confirm items and choose to place order
    Order->>MenuItem: ask for current price
    MenuItem-->>Order: return current price
    Order-->>Customer: display order total and ask for confirmation
    Customer->>Order: confirm order
    Order->>Order: mark order as submitted
    Order->>Barista: make submitted order available
    Barista-->>Order: receive submitted order
    Order-->>Customer: confirm order was submitted


2. Then draw the price change. Two short diagrams or one, your choice: the manager changing the price, and Sam's order being totalled. Follow the arrow that reads the price and say exactly which object it lands on.

```mermaid
sequenceDiagram
    actor Manager
    actor Sam
    participant MenuItem
    participant Order

    Manager->>MenuItem: change price
    MenuItem-->>Manager: confirm price was changed

    Sam->>Order: confirm items and choose to place order
    Order->>MenuItem: ask for current price
    MenuItem-->>Order: return current price
    Order-->>Sam: display order total



3. State what your model says Sam paid, honestly. If it charges him five dollars, write that down. If you cannot tell, write that down — being unable to tell is itself the answer.

Price would get updated by the time sam pays, so he pays updated price. Also not sure. 

---

## 2. What I'm Not Sure About

*One or two sentences. What feels uncertain or wrong about what you just produced?*

*This is about your own thinking, and it is required every week. It is not the same thing as a question written for a stakeholder. When the prompt asks you for one of those, it belongs up in section 1 with the step that asked for it, and it does not replace this section.*

Not sure if I answered question 3 correctly

---

**Commit this file before group discussion begins.**