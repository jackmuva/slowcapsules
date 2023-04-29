import StandardApi from "./StandardApi";

class WriterApi extends StandardApi{

    postNewWriter(writer){
        return this.post('/api/writer/new', writer).then(this.json);
    }
    deleteWriter(writerId){
        return this.delete(`/api/writer/${writerId}`)
    }
    getWriter(penName){
        return this.get(`api/writer/get/${penName}`).then(this.json)
    }
}
export default new WriterApi();