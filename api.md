📘 API Documentation
🔹 Student APIs
Method	Endpoint	                    Description	                            Access
GET	    /api/students/me	            View own profile	                    Authenticated
PUT	    /api/students/update	        Update student profile	                Authenticated
POST    /api/students/book	            Register a new room booking	            Authenticated
GET	    /api/students/bookings	        View current room booking	            Authenticated
DELETE	/api/students/booking	        Cancel current booking	                Authenticated
GET	    /api/students/payments	        View payment history	                Authenticated
POST	/api/students/pay	            Make a payment for specific month/year	Authenticated

🔹 Warden APIs
Method	    Endpoint	                Description	                    Access
POST        /api/wardens/save-details   Add warden details	            Warden
GET	        /api/wardens/students	    View all registered students	Warden
PUT	        /api/wardens/approve/{id}	Approve room booking by ID	    Warden
PUT	        /api/wardens/reject/{id}	Reject room booking by ID	    Warden

🔹 Admin APIs
Method	     Endpoint	            Description	                Access
POST        /api/admin/rooms/add	Add a new room	            Admin
POST	    /api/admin/wardens/add	Add a new warden	        Admin
GET	        /api/admin/wardens	    View all wardens	        Admin
DELETE	    /api/admin/wardens/{id}	Delete a warden by ID	    Admin

🔹 Common APIs (Public)
Method	Endpoint	            Description
GET	    /api/rooms/available	Check available rooms (for guests)
POST    /register            Register a new user (Student)
POST   /login               User login (Admin, Warden, Student)

/api/student/register:
Request Body:
{
"id": 9007199254740991,
"name": "string",
"guardianName": "string",
"guardianContactNumber": "string",
"gender": "string",
"phone": "8499407338",
"department": "string",
"collegeName": "string",
"address": "string"
}

/api/student/update
Request Body:
{
"guardianContactNumber": "string",
"phone": "8040929226",
"address": "string"
}

/api/student/pay
Request Body:
{
"id": 9007199254740991,
"month": "string",
"year": 2000,  // month and year should be a chooser for month and year
"amount": 0.1,
"paymentDate": "2025-04-21" // date choose
}

/api/student/profile
response body:
{
"id": number,
"name": "string",
"guardianName": "string",
"guardianContactNumber": "string",
"gender": "string",
"phone": "string",
"department": "string",
"collegeName": "string",
"address": "string"
}

/api/student/payments/history
Response body
[
{
"id": number,
"month": "string",
"year": number,
"amount": number,
"paymentDate": "string"
},,,
]

/api/student/bookings

Response body
Download
{
"status": "string",
"requestDate": "string",
"room": {
"roomNumber": "string"
},
"id": number
}

/hostels/available
Response body
[
{
"contactNumber": "string",
"name": "string",
"location": "string",
"id": number
},, list of hostels
]

WARDEN:
/api/warden/save-details POST
Request Body:
{
"name": "string",
"contactNumber": "string",
"address": "string"
}

/api/warden/students GET
Response body
[
{
"name": "string",
"address": "string",
"gender": "string",
"phone": "string",
"guardianContactNumber": "string",
"guardianName": "string",
"department": "string",
"collegeName": "Integral University, Lucknow"
},
]

/api/warden/approve/{id} PUT
first it gets all bookings from their de