# Individual Sketch — Week 3 Round 2
**Student:** Kaashif Khan
**Date:** 9/10/2026

---

## My Answer

*Respond directly to the prompt. Write in plain sentences — no need to be formal. You have 12 minutes total, so think first, then write.*

Customer Use Cases: 
- View the menu, availability, and prices (2.1)
- Build on an order by adding items (2.2)
- Order multiple of the same drink (2.3)
- Remove an item before placing an order (2.4)
- Change an item before placing an order (2.4)
- Place an order (2.5)
- Receive order number and confirmation (2.5)
- Add a note to an order (2.6)
- Correct mistakes in an order (2.7)
- Receive automatic discount if they are a loyalty member (2.8)

Possible Missing Customer Use Cases:
- View the current order before placing it. I got this from thinking about how a customer would check that everything on their order is correct before submitting the order.
- Cancel an entire order while it is still being built. I got this from thinking about a normal ordering process. Requirement 2.4 says a customer can remove or change an individual item before placing an order, but it never states whether the customer can cancel the entire order completely. 

---

## Diagram

*Include your Mermaid diagram below. If the prompt doesn't ask for a diagram, delete this section.*

```mermaid
flowchart LR
    Customer[Customer]

    ViewMenu([View Menu])
    BuildOrder([Build Order])
    OrderMultiple([Order Multiple of Same Drink])
    RemoveItem([Remove Item])
    ChangeItem([Change Item])
    PlaceOrder([Place Order])
    ReceiveConfirmation([Receive Order Number and Confirmation])
    AddNote([Add Note to Order])
    CorrectMistake([Correct Mistake in Order])
    ReceiveDiscount([Receive Loyalty Discount])
    ViewCurrentOrder([View Current Order])
    CancelOrder([Cancel Entire Order])

    Customer --> ViewMenu
    Customer --> BuildOrder
    Customer --> OrderMultiple
    Customer --> RemoveItem
    Customer --> ChangeItem
    Customer --> PlaceOrder
    Customer --> ReceiveConfirmation
    Customer --> AddNote
    Customer --> CorrectMistake
    Customer --> ReceiveDiscount
    Customer --> ViewCurrentOrder
    Customer --> CancelOrder

---

## What I'm Not Sure About

*One or two sentences. What feels uncertain or wrong about what you just produced?*

I'm not sure if viewing the order and cancelling the entire order should be seperate use cases. 

---

**Commit this file before group discussion begins.**
