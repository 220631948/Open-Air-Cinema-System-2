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
        payNow() {
            this.paymentStatus = 'Payment successful';
        },


        submitTicket() {
            this.tickets.push({...this.ticketForm});

            this.ticketForm = {
                ticketID: null,
                bookingID: null,
                seatNumber: '',
                type: '',
                price: null,
                date: ''
            };
        },
        submitPayment() {
            const paymentID = Date.now();

            // Handle payment logic here
            this.paymentForm = {
                bookingID: null,
                amount: null,
                paymentMethod: '',
                date: ''
            }
        }
    }
}