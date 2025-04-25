📘 API Documentation
🔹 Student APIs
Method	Endpoint	                    Description	                            Access
GET	    /api/students/me	            View own profile	                    Authenticated
PUT	    /api/students/update	        Update student profile	                Authenticated
POST    /api/students/book	        Register a new room booking	            Authenticated
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