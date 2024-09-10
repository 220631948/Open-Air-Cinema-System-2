<template>
  <div id="app">
    <header>
      <div class="logo">
        <img src="@/assets/logo.png" alt="Logo" class="logo-image" />
        <h1 class="brand-name">OpenAirCinema</h1>
      </div>
      <nav>
        <router-link to="/">Home</router-link>
        <router-link to="/login">Login</router-link>
      </nav>
    </header>
    <router-view />
  </div>
</template>

<script>
export default {
  name: 'App',
};
</script>

<style scoped>
/* Container for header */
header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 10px 20px;
  background-color: #f8f9fa;
  border-bottom: 2px solid #e7e7e7;
}

/* Logo and brand name container */
.logo {
  display: flex;
  align-items: center;
}

/* Logo image */
.logo-image {
  height: 40px;
  margin-right: 10px;
}

/* Brand name styling */
.brand-name {
  font-size: 24px;
  margin: 0;
  color: #333;
}

/* Navigation links */
nav {
  display: flex;
  align-items: center;
}

/* Navigation links styling */
nav a {
  margin-left: 20px;
  text-decoration: none;
  color: #007bff;
  font-weight: bold;
}

/* Styling for hover and active states */
nav a:hover {
  color: #0056b3;
}

nav a.router-link-active {
  color: #0056b3;
  text-decoration: underline;
}
</style>
