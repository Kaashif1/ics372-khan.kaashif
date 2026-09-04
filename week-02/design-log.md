# Design Log — Week [2]
### [ICS 372 Object Oriented Design and Implementation] | [Fall Semester]
**Student:** [Kaashif Khan]  
**Group:** [Group Number: 1]  
**Date:** [9/3/2026]  
**Topic:** [Tonight's topic — Domain Analysis]

---

## Part 1 — The Problem

*In 2-3 sentences, describe the design problem you worked on tonight in your own words. Do not copy the handout. What were you actually trying to figure out?*

[Tonight we were figuring out how to represent a coffee shop point-of-sale system. We were deliberating on what information and behavior each object should have. Later on in the class we also honed in on how an order changes over time, how it transitions from state to state.]

---

## Part 2 — Your Design Decision

*What did your group decide? Describe the design you landed on clearly enough that someone who wasn't in your group could understand it. If you produced a diagram, reference it here by filename.*

[Our group decided to make an abstract User class with Manager, Employee, and Customer as subclasses because they share information such as a name and ID. We also made Order, Item, and Inventory classes while describing the relationships between them. We decided that the Order class should have an orderId, employeeId, customerId, list of items, state, and totalPrice. Also, during the state of an Order being created, we decided orderId and customerId should remain unchanged, meanwhile state and employeeId can be changed.]

---

## Part 3 — How You Got There

*This is the most important section. Start from your individual sketch — what did you think before your group talked? Then walk through how the group discussion changed (or didn't change) your thinking. What did the problem itself tell you about what the design needed to do? What constraints or requirements drove your decisions?*

*Avoid starting from a solution. If you found yourself thinking "we should use X pattern" or "this should be a Y" before fully understanding the problem, note that here and explain whether you went back to the problem or pushed forward anyway.*

[In my first individual sketch I seperated Customer, Employee, and Manager into their own classes because I was thinking about their different resposibillities. The other classes I included were Menu, MenuItem, Order, and Inventory. Inventory was a class that I suggested to the group, since none of my group members initially had thought of it. After my suggestion we decided that Inventory is important because it keeps track of available items and their quantities. 

An idea that my group brought up which differed to my initial design was creating a
shared User class. In my individual sketch I had Customer, Employee, and Manager as being seperate classes. We realized that they could actually be subclasses of User, since they should share common information such as a name and ID.

In my second individual sketch I focused on how an Order changes over time. Originally, I had it as a customer building the order, the order being prepared, the order being complete and picked up. I thought the status and employeeID would change as the order moved through the system while orderID, items, and totalPrice would stay the same. To move through these states I added the methods addItem(), beginOrder(), submitOrder(), finishOrder(), and pickupOrder(). During the group discussion we decided customerId should remain immutable, meanwhile the items and price can change while the customer is working on building their order. After the order is placed, then the items should no longer be able to be edited.]

---

## Part 4 — The Road Not Taken

*What other approaches did you consider and why did you move away from them? If your group disagreed about something, describe both positions and explain how you resolved it. If you personally favored a different approach than what the group decided, explain your reasoning — even if you were outvoted.*

[In my first sketch I created Customer, Employee, and Manager as seperate classes because I was focused on what each one does. After discussing with my group, it was brought to my attention that they share some basic information and could inherit from a common parent User class. If I were to redo this activity I'd also think more carefully about which fields in the Order class should be allowed to change at different stages instead of assuming they always stay the same.]

---

## Part 5 — What You're Uncertain About

*What would you change about your design if you had more time or information? What are you not confident about? What might break later?*

[I'm unsure about how inventory should connect to the rest of the system. I included inventory in my first sketch because the system has to be able to keep track of what's avaialable, but I didn't decide if inventory should track menu items directly or track ingredients needed to make those items. With more time, I'd figure out how placing an order should affect inventory and what should happen if a customer chooses something that is not in stock. I believe this part of my design could cause problems.]

---

## Word Count: [X words]

---

*Aim for 300–500 words across Parts 2–5. Part 1 does not count toward the word count.*
