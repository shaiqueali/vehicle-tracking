# Vehicle Tracking API

 - ## Vehicle tracking system

The solutions need to be able to track the position of the vehicle using GPS navigation. A device onboarded in a
vehicle will communicate with your API to register the vehicle and update its position.

 - ## API
Implement a RESTful API to track vehicles location. The device will update the API every 30 seconds with the
new location.
 - Register a vehicle
 - Record its position
When the frontend team will implement the back office for the vehicle tracking, any authenticated administrator
should be able to :
 - Retrieve the current position of a vehicle
 - Retrieve the positions of a vehicle during a certain time, in order to display their journey on a map
(maps drawing is out of scope)

 - ## Database / scalability
There will be 10,000 vehicles equipped with the device. We need to ensure the solution is scalable and the
database correctly designed for that amount of records.

 - ## Extensibility
If the customer wants to store more properties (fuel, speed, etc.). How do we extend the data model to support
it?

- ## Security
We need to ensure a device or user cannot update the position of another vehicle.
As a backend engineer, your tasks will be to :
- Design the database and data models
-  Implement the REST API

As a backend engineer, you will pay special attention to :
-  Design
- Security
- Performance
- Scalability
- Maintainability
- Documentation: None, unless mathematical algorithms
Any assumptions, specific reason for a particular choice must be described in the code.
