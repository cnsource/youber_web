var registe_form = new Vue({
    el: '#registe_form',
    data: {
        username:"",
        pwd1:"",
        pwd2:"",
    },
    methods: {
        registe : function (){
            axios
                .post('/registe',{
                    username: registe_form.username,
                    password: registe_form.pwd1
                })
                .then((response)=>{
                    console.log(response.data)
                })
        }
    }
})