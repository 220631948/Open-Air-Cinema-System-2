// _TicketPayment.js

import {FontAwesomeIcon} from '@fortawesome/vue-fontawesome'
import {library} from '@fortawesome/fontawesome-svg-core'
import {faTicket, faCreditCard, faSave, faList} from '@fortawesome/free-solid-svg-icons'
//import {mapState, mapActions} from 'vuex'

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
                date: ''
            },
            paymentForm: {
                bookingID: null,
                amount: null,
                paymentMethod: '',
                date: ''
            }
        }
    },

    computed: {},

    methods: {
        // for testing purpose
        payNow() {
            this.paymentStatus = 'Payment successful';
        },

        submitTicket() {
            // Simulate API call to save ticket
            const newTicket = {...this.ticketForm, ticketID: Date.now(), bookingID: Date.now()};
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
                additionalInfo: ''
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