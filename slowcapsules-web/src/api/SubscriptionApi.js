import StandardApi from "./StandardApi";

class SubscriptionApi extends StandardApi{

    postNewSubscription(subscription){
        return this.post('/api/subscription/new', subscription).then(this.json);
    }

}
export default new SubscriptionApi();