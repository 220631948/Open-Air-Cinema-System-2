<!-- TicketPayment.vue -->



<template>
  <div class="container mt-4 TicketPayment">
    <h1 class="mb-4">Ticket and Payment Management</h1>

    <!-- Ticket List -->
    <section class="mb-5">
      <h2 class="mb-3"><font-awesome-icon icon="list" /> Available Tickets</h2>
      <div class="row row-cols-1 row-cols-md-3 g-4">
        <div v-for="ticket in tickets" :key="ticket.ticketID" class="col">
          <div class="card h-100">
            <div class="card-header bg-primary text-white">
              <h5 class="card-title mb-0">Ticket #{{ ticket.ticketID }}</h5>
            </div>
            <div class="card-body">
              <p class="card-text"><strong>Type:</strong> {{ ticket.type }}</p>
              <p class="card-text"><strong>Price:</strong> ${{ ticket.price.toFixed(2) }}</p>
              <p class="card-text"><strong>Seat:</strong> {{ ticket.seatNumber }}</p>
              <p class="card-text"><strong>Booking ID:</strong> {{ ticket.bookingID }}</p>
              <!-- <p class="card-text"><strong>Additional Info:</strong> {{ ticket.additionalInfo }}</p> -->
            </div>
            <div class="card-footer">
              <small class="text-muted">Date: {{ ticket.date }}</small>
            </div>
          </div>
        </div>
      </div>
    </section>

    <div class="row">
      <!-- Ticket Form -->
      <div class="col-md-6">
        <h2><font-awesome-icon icon="ticket" /> Add Ticket</h2>
        <form @submit.prevent="submitTicket">

          <div class="mb-3">
            <label for="bookingID" class="form-label">Booking ID:</label>
            <input v-model="ticketForm.bookingID" type="number" class="form-control" id="bookingID" required>
          </div>
          <div class="mb-3">
            <label for="seatNumber" class="form-label">Seat Number:</label>
            <input v-model="ticketForm.seatNumber" type="text" class="form-control" id="seatNumber" required>
          </div>
          <div class="mb-3">
            <label for="ticketType" class="form-label">Ticket Type:</label>
            <input v-model="ticketForm.type" type="text" class="form-control" id="ticketType" required>
          </div>
          <div class="mb-3">
            <label for="ticketPrice" class="form-label">Price:</label>
            <input v-model="ticketForm.price" type="number" step="0.01" class="form-control" id="ticketPrice" required>
          </div>
          <div class="mb-3">
            <label for="ticketDate" class="form-label">Date:</label>
            <input v-model="ticketForm.date" type="date" class="form-control" id="ticketDate" required>
          </div>
          <button type="submit" class="btn btn-primary">
            <font-awesome-icon icon="save" /> Save Ticket
          </button>
        </form>
      </div>

      <!-- Payment Form -->
      <div class="col-md-6 pt-3">
        <h2><font-awesome-icon icon="credit-card" /> Process Payment</h2>
        <form @submit.prevent="submitPayment">
          <div class="mb-3">
            <label for="amount" class="form-label">Amount:</label>
            <input v-model="paymentForm.amount" type="number" step="0.01" class="form-control" id="amount" required>
          </div>
          <div class="mb-3">
            <label for="paymentMethod" class="form-label">Payment Method:</label>
            <input v-model="paymentForm.paymentMethod" type="text" class="form-control" id="paymentMethod" required>
          </div>
          <div class="mb-3">
            <label for="date" class="form-label">Date:</label>
            <input v-model="paymentForm.date" type="date" class="form-control" id="date" required>
          </div>
          <button type="submit" class="btn btn-primary">
            <font-awesome-icon icon="save" /> Process Payment
          </button>
        </form>
      </div>
    </div>
  </div>
</template>

<script src="./_TicketPayment.js" lang="js"></script>
<style src="./_TicketPayment.css" lang="css" scoped></style>