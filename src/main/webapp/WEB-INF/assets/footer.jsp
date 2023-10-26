<script>
    document.addEventListener("DOMContentLoaded", function () {
        const menuButton = document.querySelector('[data-collapse-toggle="mobile-menu"]');
        const mobileMenu = document.getElementById('mobile-menu');

        menuButton.addEventListener("click", function () {
            mobileMenu.classList.toggle("hidden");
        });
    });
</script>
</body>
</html>
