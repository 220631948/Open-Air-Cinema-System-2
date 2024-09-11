// _TicketPayment.js

import {FontAwesomeIcon} from '@fortawesome/vue-fontawesome'
import {library} from '@fortawesome/fontawesome-svg-core'
import {faTicket, faCreditCard, faSave, faList} from '@fortawesome/free-solid-svg-icons'

library.add(faTicket, faCreditCard, faSave, faList)

export default {
    name: 'TicketPayment',

    components: {
        FontAwesomeIcon
    },

    data() {
        return {
            tickets: [],
            ticketForm: {
                ticketID: null,
                bookingID: null,
                seatNumber: '',
                type: '',
                price: null,
                date: '',
                includes: []
            },
            paymentForm: {
                bookingID: null,
                amount: null,
                paymentMethod: '',
                date: ''
            }
        }
    },

    methods: {
        bookNow(ticket) {
            alert(`Booking ticket #${ticket.ticketID} for $${ticket.price.toFixed(2)}`);
        },

        submitTicket() {
            // Simulate API call to save ticket
            const newTicket = {...this.ticketForm, ticketID: Date.now(), bookingID: Date.now()};
            newTicket.includes = newTicket.includes.split(',').map(item => item.trim());
            this.tickets.push(newTicket);
            this.resetTicketForm();
        },
        submitPayment() {
            // Simulate API call to process payment
            const paymentID = Date.now();
            console.log('Payment processed:', {...this.paymentForm, paymentID});
            this.resetPaymentForm();
        },
        resetTicketForm() {
            this.ticketForm = {
                seatNumber: '',
                type: '',
                price: null,
                date: '',
                includes: ''
            };
        },
        resetPaymentForm() {
            this.paymentForm = {
                amount: null,
                paymentMethod: '',
                date: ''
            };
        }
    }
};