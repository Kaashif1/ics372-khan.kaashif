# Brew & Byte ��� System Requirements
### Prepared by the owner �� Version 1.0

*This is what I want the system to do. I've numbered everything so you can point at it when you ask me questions.*

---

## 1. General

**1.1** The system runs on a computer at the counter, a tablet the baristas use behind the bar, and my laptop in the back office.
Non Functional 

**1.2** It should be fast and easy to use. My staff turn over a lot and I don't want to spend a week training somebody.
Non Functional 

**1.3** Nothing should ever get lost. If the power goes out I need everything back when it comes on.
Non Functional 

**1.4** When a customer places an order I want it on the barista's tablet right away. Not thirty seconds later.
Non Functional 

---

## 2. Customers

**2.1** A customer can look at the menu and see what's available and what it costs.
Functional 

**2.2** A customer can build an order by adding items to it. There should be a dropdown for size and checkboxes for the milk and syrup options.
Functional 

**2.3** A customer can order more than one of the same drink.
Functional 

**2.4** A customer can change their mind about an item before they've placed the order ��� take it off, change the size, whatever.
Functional 

**2.5** When the customer is happy with the order they place it, and they get a confirmation with a number on it so they know which order is theirs when it comes up.
Functional 

**2.6** A customer can add a note to an order, like "extra hot" or "no lid."
Functional 

**2.7** If a customer realizes they made a mistake, they should be able to fix their order and we'll sort it out.
Functional - cannot fully build from - Conflicts with 3.4

**2.8** Loyalty members get their discount automatically. They shouldn't have to ask.
Functional - cannot fully build from 

---

## 3. Baristas

**3.1** The barista sees the orders that need to be made.
Functional 

**3.2** Orders should be prepared in the order in which they were placed.
Functional 

**3.3** The barista marks an order complete when it's ready for pick up, and then the customer's number gets called.
Functional 

**3.4** Once an order has been placed it goes to the barista queue and it can't be changed, because otherwise the barista is making something that isn't what the customer wants anymore.
Functional 

**3.5** The barista needs to see everything about the order: the customizations, the notes, all of it. We had a real problem with this at the old place.
Functional 

**3.6** If we're out of something the barista needs to be able to tell the system, so it stops showing up on the menu.
Functional 

**3.7** If the manager isn't in, a barista can add a menu item. This happens more than you'd think, somebody has an idea for a special and I'm not there.
Functional - Cannot fully build from - Conflicts with 4.1

---

## 4. Managers

**4.1** Only managers can modify the menu.
Functional 

**4.2** A manager can add a menu item, change the price of one, or take one off the menu.
Functional 

**4.3** A manager can restock an ingredient ��� say we get a milk delivery, I go in and tell it we have more milk now.
Functional 

**4.4** The system should warn me when inventory is getting low so I can order more before we run out.
Functional - cannot fully build from 

**4.5** At the end of the day I want to look at the day's sales ��� what sold, how much money came in.
Functional 

**4.6** I want to be able to see how long orders are taking. If we're slow at eleven in the morning I want to know that.
Functional

---

## 5. Things I know I haven't decided

**5.1** I don't know yet whether we're doing anything with payment. Assume we aren't for now.
Neither

**5.2** I don't want to deal with scheduling staff in this thing. That stays on paper.
Neither

---

*If something in here doesn't make sense or I've contradicted myself, that's probably true. Ask me, or make a decision and write down what you decided.*

## Cannot fully build from
2.7 Are customers allowed to fix an order because 3.4 says a placed order can't be changed? 
2.8 Discount amount and rules are not specified 
3.7 Conflicts with 4.1 because 4.1 says only managers can modify the menu, while 3.7 allows the barista to add menu items
4.4 Inventory threshold needs to be specified 

## Question for owner 
Can baristas add menu items when a manager isn't present, or should that permission be only for the managers? (Contradiction between 3.7 and 4.1)

    