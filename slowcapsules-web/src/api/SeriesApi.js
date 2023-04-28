import StandardApi from "./StandardApi";

class SeriesApi extends StandardApi{
    postSeries(series){
        return this.post('/api/series/new', series).then(this.json);
    }
    deleteSeries(seriesId){
        return this.delete(`/api/series/delete/${seriesId}`).then(this.json);
    }
    getNewestSeries(){
        return this.get('/api/series/getNewest').then(response => response.json());
    }

    getSeriesByWriter(writer){
        return this.get(`/api/series/writer/${writer}`).then(this.json);
    }
    getSeriesByTag(tag){
        return this.get(`/api/series/tag/${tag}`).then(this.json);
    }
    getSeriesByKeyword(keyword){
        return this.get(`api/series/search/${keyword}`).then(this.json);
    }
}
export default new SeriesApi();