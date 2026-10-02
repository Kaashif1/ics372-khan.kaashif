# Design Log — Week 6
### ICS 372 Object Oriented Programming | Fall Semester
**Student:** Kaashif Khan
**Group:** 1
**Date:** 10/1/2026
**Topic:** Conceptual to software classes — interfaces, abstract classes, polymorphism

---

## Part 1 — The Problem

The problem was figuring out how the entities from our domain model should translate into actual software classes. We had to decide which entities should remain as individual classes, which should be split into multiple classes and where interfaces or abstract classes would make sense.

---

## Part 2 — Your Design Decision

Our group decided that most of our domain entities would become individual classes, but Item would be split into MenuItem and OrderItem. MenuItem represents an item as it appears on the menu, while OrderItem represents an item placed on an order, with price and selected customizations included. We also added Ingredient and Customization classes and decided to use User as an interface and Item as an abstract class. These decisions are represented in `docs/week-06/group-artifact.md`.

---

## Part 3 — How You Got There

In my individual sketch, I originally mapped every domain entity to one class. I also thought User could be an abstract class shared by Customer and Employee. Furthermore, Employee could be an abstract class shared by Manager and Barista. During the group discussion, we agreed that Item needed to become two classes because the information about an item on the menu is different from the information that needs to stay with an item after it is ordered. This is what led us to splitting it into MenuItem and OrderItem. We also added Ingredient so that Inventory could directly keep track of ingredients, and Customization so we could distinguish possible customizations from the customizations actually selected for an order. We also decided User should be an interface because the different types of users did not have enough shared behavior to justify using it as an abstract class.

---

## Part 4 — The Road Not Taken

One alternative was my idea of keeping Item as just a single class. We moved away from that because a menu item can change while an already placed order still needs to keep information such as price and customizations. Splitting it into MenuItem and OrderItem turned out to be the better decision. Another possibility was making User an abstract class instead of an interface. I did that in my individual sketch because Customer and Employee shared information such as a name and ID. Our group instead chose an interface because the user types had little shared behavior. 

---

## Part 5 — What You're Uncertain About

I'm still unsure how the user classes should eventually interact with the system's UI. We discussed whether there should be a UI class between users and classes such as Inventory, or whether different users will need separate UI classes or views. I'm also unsure whether the Item hierarchy should remain an abstract class hierarchy or eventually work better as an interface. 

---

## Word Count: 380