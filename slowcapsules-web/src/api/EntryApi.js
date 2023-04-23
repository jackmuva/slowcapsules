import StandardApi from "./StandardApi";

class EntryApi extends StandardApi{
    postNewEntry(entry){
        return this.post('/api/entry/new', entry).then(this.json);
    }
    deleteEntry(id){
        return this.delete(`/api/entry/delete/${id}`).then(this.json);
    }
    getEntriesBySeriesId(id){
        return this.get(`/api/entry/getBySeries/${id}`).then(this.json);
    }
}
export default new EntryApi();