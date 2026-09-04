# Individual Sketch 2 by Kaashif Khan

## Order

### State 1 - Customer is Building the Order, but hasn't been submitted yet

Fields:
- orderID: integer
- customerID: integer
- items: list of MenuItems
- totalPrice: decimal
- status: "Building Order"

### State 2 - Order is Being Prepped

Fields:
- orderID: integer
- items: list of MenuItems
- totalPrice: decimal
- status: "Order in Progress"
- customerID: integer
- employeeID: integer

### State 3 - Order Complete and Picked Up

Fields:
- orderID: integer
- items: list of MenuItems
- totalPrice: decimal
- status: "Picked Up"
- customerID: integer
- employeeID: integer

## What Changes Between States?

What changes is the status of the order as it's being moved through the system.
(Building, in progress, picked up)
Also, employeeID is assigned when the employee begins the order.


## What Stays the Same?

The orderID stays the same. 
The items and totalPrice also stay the same.


## Methods Needed for order to change state

addItem() - This adds menuItem to the order while the customer is building it.
submitOrder() - This submits the order which moves it out of the  uilding state.
beginOrder() - This changes status to order in progress when an employee starts preparing the order. 
finishOrder() - This labels the order as complete after the employee finishes preparing. 
pickupOrder() - This changes the status to Picked Up when the customer receives his/her order. 