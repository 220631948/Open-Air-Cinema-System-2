<template>
  <div>
    <h1>Customer Information</h1>
    <form @submit.prevent="saveCustomer">
      <div>
        <label for="name">First Name:</label>
        <input type="text" id="name" v-model="customer.name" required />
      </div>
      <div>
        <label for="surname">Surname:</label>
        <input type="text" id="surname" v-model="customer.surname" required />
      </div>
      <div>
        <label for="email">Email:</label>
        <input type="email" id="email" v-model="customer.email" required />
      </div>
      <div>
        <label for="phone">Phone:</label>
        <input type="text" id="phone" v-model="customer.phone" required />
      </div>
      <div>
        <label for="password">Password:</label>
        <input type="password" id="password" v-model="customer.password" required />
      </div>
      <button type="submit">Save</button>
    </form>
    <button @click="logout">Logout</button>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      customer: {
        name: '',
        surname: '',
        email: '',
        phone: '',
        password: ''
      }
    };
  },
  methods: {
    saveCustomer() {
      // Use the /customer/create endpoint to save the customer data
      axios.post('/customer/create', this.customer)
          .then(response => {
            alert('Customer information saved successfully!');
          })
          .catch(error => {
            console.error(error);
            alert('There was an error saving the customer information.');
          });
    },
    logout() {
      localStorage.removeItem('authenticated');
      this.$router.push('/login');
    }
  }
};
</script>

<style scoped>
form {
  display: flex;
  flex-direction: column;
  width: 300px;
}

form div {
  margin-bottom: 10px;
}

button {
  margin-top: 20px;
}
</style>
