<template>
  <div>
    <h1>Login</h1>
    <form @submit.prevent="login">
      <div>
        <label for="email">Email:</label>
        <input v-model="email" type="email" id="email" required />
      </div>
      <div>
        <label for="password">Password:</label>
        <input v-model="password" type="password" id="password" required />
      </div>
      <button type="submit">Login</button>
    </form>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      email: '',
      password: '',
    };
  },
  methods: {
    login() {
      if (this.email === 'user@example.com' && this.password === 'password') {
        localStorage.setItem('authenticated', 'true');
        this.$router.push('/');
      } else {
        alert('Invalid credentials');
      }
    }, test() {axios.get('http://localhost:9090/customer/getAll')},
  },
};
</script>
