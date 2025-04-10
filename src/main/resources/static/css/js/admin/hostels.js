document.addEventListener('DOMContentLoaded', function() {
    // Delete Hostel Functionality
    const deleteButtons = document.querySelectorAll('.delete-btn');
    const deleteModal = document.getElementById('deleteModal');
    const deleteForm = document.getElementById('deleteForm');
    const deleteId = document.getElementById('deleteId');
    const cancelDelete = document.getElementById('cancelDelete');

    if (deleteButtons.length > 0 && deleteModal && deleteForm && deleteId) {
        deleteButtons.forEach(button => {
            button.addEventListener('click', function() {
                const id = this.getAttribute('data-id');
                deleteId.value = id;
                deleteModal.classList.add('active');
            });
        });

        if (cancelDelete) {
            cancelDelete.addEventListener('click', function() {
                deleteModal.classList.remove('active');
            });
        }

        deleteModal.addEventListener('click', function(e) {
            if (e.target === deleteModal) {
                deleteModal.classList.remove('active');
            }
        });
    }
});