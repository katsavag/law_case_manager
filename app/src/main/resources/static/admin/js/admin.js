// Admin Panel JavaScript

$(document).ready(function() {
    // Initialize DataTables
    if ($.fn.DataTable) {
        $('.js-datatable').DataTable({
            language: {
                search: "Αναζήτηση:",
                lengthMenu: "Εμφάνιση _MENU_ εγγραφών",
                info: "Εμφάνιση _START_ έως _END_ από _TOTAL_ εγγραφές",
                infoEmpty: "Εμφάνιση 0 έως 0 από 0 εγγραφές",
                infoFiltered: "(φιλτραρισμένες από _MAX_ συνολικές εγγραφές)",
                paginate: {
                    first: "Πρώτη",
                    last: "Τελευταία",
                    next: "Επόμενη",
                    previous: "Προηγούμενη"
                },
                emptyTable: "Δεν υπάρχουν διαθέσιμα δεδομένα"
            },
            pageLength: 25,
            order: [[0, 'asc']]
        });
    }

    // Auto-hide alerts after 5 seconds
    setTimeout(function() {
        $('.alert').fadeOut('slow');
    }, 5000);

    // Confirm delete actions
    $('.js-confirm-delete').on('click', function(e) {
        if (!confirm('Είστε σίγουροι ότι θέλετε να διαγράψετε αυτό το στοιχείο;')) {
            e.preventDefault();
        }
    });
});
