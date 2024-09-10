<template>
  <div>
    <h1>Welcome to the OpenAirCinema</h1>
    <button @click="logout">Logout</button>
  </div>
</template>

<script>
export default {
  methods: {
    logout() {
      localStorage.removeItem('authenticated');
      this.$router.push('/login');
    },
  },
};
</script>
