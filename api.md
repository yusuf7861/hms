📘 API Documentation
🔹 Student APIs
Method	Endpoint	                    Description	                            Access
POST	/api/students/register	        Register a new student	                Public
POST	/api/students/login	            Student login	                        Public
GET	    /api/students/me	            View own profile	                    Authenticated
PUT	    /api/students/update	        Update student profile	                Authenticated
POST    /api/students/booking	        Register a new room booking	            Authenticated
GET	    /api/students/booking	        View current room booking	            Authenticated
DELETE	/api/students/booking	        Cancel current booking	                Authenticated
GET	    /api/students/payments	        View payment history	                Authenticated
POST	/api/students/pay	            Make a payment for specific month/year	Authenticated

🔹 Warden APIs
Method	    Endpoint	                Description	                    Access
POST	    /api/wardens/login	        Warden login	                Public
GET	        /api/wardens/students	    View all registered students	Warden
PUT	        /api/wardens/approve/{id}	Approve room booking by ID	    Warden
PUT	        /api/wardens/reject/{id}	Reject room booking by ID	    Warden

🔹 Admin APIs
Method	     Endpoint	            Description	                Access
POST	    /api/admin/login	    Admin login	                Public
POST        /api/admin/rooms/add	Add a new room	            Admin
POST	    /api/admin/wardens/add	Add a new warden	        Admin
GET	        /api/admin/wardens	    View all wardens	        Admin
DELETE	    /api/admin/wardens/{id}	Delete a warden by ID	    Admin

🔹 Common APIs (Public)
Method	Endpoint	            Description
GET	    /api/rooms/available	Check available rooms (for guests)