import StandardApi from "./StandardApi";

class EntryApi extends StandardApi{
    postNewEntry(entry){
        return this.postWithAuth('/api/entry/new', entry).then(response => response.json());
    }

    updateEntry(entry){
        return this.putWithAuth('/api/entry/update', entry).then(response => response.json());
    }
    deleteEntry(id){
        return this.delete(`/api/entry/delete/${id}`).then(response => response.json());
    }
    getEntriesBySeriesId(id){
        return this.get(`/api/entry/getBySeries/${id}`).then(response => response.json());
    }
}
export default new EntryApi();