document.addEventListener('DOMContentLoaded', function() {
    // Sidebar Toggle
    const sidebarToggle = document.getElementById('sidebarToggle');
    const sidebar = document.querySelector('.sidebar');

    if (sidebarToggle && sidebar) {
        sidebarToggle.addEventListener('click', function() {
            sidebar.classList.toggle('active');
        });
    }

    // Notifications Dropdown
    const notificationBtn = document.querySelector('.notification-btn');
    const notificationDropdown = document.querySelector('.notification-dropdown');

    if (notificationBtn && notificationDropdown) {
        notificationBtn.addEventListener('click', function(e) {
            e.stopPropagation();
            notificationDropdown.classList.toggle('active');
        });

        document.addEventListener('click', function(e) {
            if (!notificationDropdown.contains(e.target) && !notificationBtn.contains(e.target)) {
                notificationDropdown.classList.remove('active');
            }
        });
    }

    // User Profile Dropdown
    const userProfile = document.querySelector('.user-profile');
    const userDropdown = document.querySelector('.user-dropdown');

    if (userProfile && userDropdown) {
        userProfile.addEventListener('click', function(e) {
            e.stopPropagation();
            userDropdown.classList.toggle('active');
        });

        document.addEventListener('click', function(e) {
            if (!userDropdown.contains(e.target) && !userProfile.contains(e.target)) {
                userDropdown.classList.remove('active');
            }
        });
    }

    // Modal Functionality
    const modalTriggers = document.querySelectorAll('[data-modal]');

    modalTriggers.forEach(trigger => {
        const modalId = trigger.getAttribute('data-modal');
        const modal = document.getElementById(modalId);

        if (modal) {
            trigger.addEventListener('click', function() {
                modal.classList.add('active');
            });

            const closeButtons = modal.querySelectorAll('[data-close-modal]');
            closeButtons.forEach(button => {
                button.addEventListener('click', function() {
                    modal.classList.remove('active');
                });
            });

            modal.addEventListener('click', function(e) {
                if (e.target === modal) {
                    modal.classList.remove('active');
                }
            });
        }
    });

    // Delete Confirmation
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