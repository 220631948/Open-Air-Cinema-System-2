<template>
  <div>
    <h1>Create an Order</h1>
    <form @submit.prevent="saveOrder">
      <div>
        <label for="customerID">Customer:</label>
        <select id="customerID" v-model="order.customerId" required>
          <option v-for="customer in customers" :key="customer.customerId" :value="customer.customerId">
            {{ customer.name }} {{ customer.surname }}
          </option>
        </select>
      </div>

      <div>
        <label for="venueID">Venue:</label>
        <select id="venueID" v-model="order.venueId" required>
          <option v-for="venue in venues" :key="venue.venueId" :value="venue.venueId">
            {{ venue.name }}
          </option>
        </select>
      </div>

      <div>
        <label for="foodItemID">Food Item:</label>
        <select id="foodItemID" v-model="order.foodItemId">
          <option v-for="foodItem in foodItems" :key="foodItem.foodItemId" :value="foodItem.foodItemId">
            {{ foodItem.name }}
          </option>
        </select>
      </div>

      <div>
        <label for="promoID">Promo Code:</label>
        <select id="promoID" v-model="order.promoId">
          <option v-for="promo in promos" :key="promo.promoId" :value="promo.promoId">
            {{ promo.code }} ({{ promo.discount }}% off)
          </option>
        </select>
      </div>

      <div>
        <label for="totalAmount">Total Amount:</label>
        <input type="number" id="totalAmount" v-model="order.totalAmount" step="0.01" required />
      </div>

      <div>
        <label for="orderDate">Order Date:</label>
        <input type="date" id="orderDate" v-model="order.orderDate" required />
      </div>

      <button type="submit">Save Order</button>
    </form>

    <button @click="logout">Logout</button>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      order: {
        customerId: '',
        venueId: '',
        foodItemId: '',
        promoId: '',
        totalAmount: 0.0,
        orderDate: ''
      },
      customers: [],
      venues: [],
      foodItems: [],
      promos: []
    };
  },
  methods: {
    saveOrder() {
      // Use the create endpoint to save the order
      axios.post('/order/create', this.order)
          .then(response => {
            alert('Order saved successfully!');
          })
          .catch(error => {
            console.error(error);
            alert('There was an error saving the order.');
          });
    },
    loadOptions() {
      // Load customers
      axios.get('/api/customers')
          .then(response => {
            this.customers = response.data;
          });

      // Load venues
      axios.get('/api/venues')
          .then(response => {
            this.venues = response.data;
          });

      // Load food items
      axios.get('/api/foodItems')
          .then(response => {
            this.foodItems = response.data;
          });

      // Load promos
      axios.get('/api/promos')
          .then(response => {
            this.promos = response.data;
          });
    },
    logout() {
      localStorage.removeItem('authenticated');
      this.$router.push('/login');
    }
  },
  created() {
    this.loadOptions(); // Load customers, venues, food items, and promos when the component is created
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
