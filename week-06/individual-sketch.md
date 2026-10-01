# Individual Sketch ��� Week 6 Round 1
**Student:** Kaashif Khan
**Date:** 10/1/2026

---

## 1. Tonight's Prompt

Work alone, from your group's `docs/design/domain-model.md`. Copy the three steps below into **section 1 of `week-06/individual-sketch.md`** and put your work directly under the step that asked for it.

**About the examples.** Every example tonight uses the library system from the textbook, not Brew & Byte. They show you the *shape* of what to write and nothing else. Don't read a design decision into them, and don't go looking for the matching thing in your own model.

### Step 1: The mapping table

| Entity | Verdict | Becomes | If no class, where it went |
|---|---|---|---|
| User | one class | User | - |
| Customer | one class | Customer | - |
| Employee | one class | Employee | - |
| Manager | one class | Manager | - |
| Barista | one class | Barista | - |
| Order | one class | Order | - |
| Menu | one class | Menu | - |
| Item | one class | Item | - |
| Inventory | one class | Inventory | - |

### Step 2: The classes

#### User
- Responsible for: representing a person that interacts with the system
- Knows: name: String; id: double
- Does: nothing, see step 3

#### Customer
- Responsible for: representing a customer who places orders.
- Knows: nothing beyond User
- Does: placeOrder(): Order

#### Employee
- Responsible for: representing an employee who works with customer orders.
- Knows: nothing beyond User
- Does: fulfillOrder(order: Order): void

#### Manager
- Responsible for: representing an employee authorized to manage the menu.
- Knows: nothing beyond User
- Does: addItem(name: String, price: double): void; removeItem(item: Item): void; updateItemPrice(item: Item, price: double): void

#### Barista
- Responsible for: representing an employee who fulfills customer orders.
- Knows: nothing beyond Employee
- Does: fulfillOrder(order: Order): void

#### Order
- Responsible for: keeping the items and customer associated with one order.
- Knows: items: List<Item>; customer: Customer
- Does: setCustomer(customer: Customer): void; setEmployee(employee: Employee): void; addItem(item: Item): void

#### Menu
- Responsible for: keeping the items currently available for purchase.
- Knows: items: List<Item>
- Does: addItem(name: String, price: double): void; removeItem(item: Item): void; updateItemPrice(item: Item, price: double): void

#### Item
- Responsible for: representing one item that can be purchased from the menu.
- Knows: name: String; price: double
- Does: nothing, see step 3

#### Inventory
- Responsible for: keeping track of ingredients and their availability.
- Knows: items: List<Item>
- Does: nothing, see step 3

### Step 3: Where you'd put a hierarchy or an interface

| Classes | What I would do | Why |
|---|---|---|
| User, Customer, Employee | abstract class User | Customers and employees are both types of users and can share the user's name and id |
| Employee, Manager, Barista | abstract class Employee | Managers and baristas both are employees but have different responsibilities in the system |
| Order, Menu, Inventory | nothing | They all contain collections, but they represent different concepts and don't share enough behavior for a useful hierarchy |

## 2. What I'm not sure about

I'm not sure whether Employee and Barista should both exist as separate classes or not. Our domain model diagram includes Employee, while the entity list describes Barista as being the employee responsible for order fulfillment. I treated Barista as a type of Employee, but I'm not sure whether Employee should remain a general parent class or if Barista should replace it.

---