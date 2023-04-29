import StandardApi from "./StandardApi";

class SubscriptionApi extends StandardApi{

    getAllSubscriptions(){
        return this.get('api/subscription/getAll').then(this.json)
    }
    postNewSubscription(subscription){
        return this.post('/api/subscription/new', subscription).then(this.json);
    }
    deleteSubscription(email, seriesId){
        return this.delete(`/api/subscription/cancelSubscription/${email}/${seriesId}`)
    }

}
export default new SubscriptionApi();