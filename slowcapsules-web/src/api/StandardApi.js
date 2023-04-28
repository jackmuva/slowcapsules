const apiUrl = "http://localhost:8090";
export default class StandardApi {

    json(response){
        return response.json().then(data => ({
            data: data,
            status: response.status
        }));
    }

    get(url){
        return fetch(apiUrl.concat(url));
    }

    delete(url){
        return fetch(apiUrl.concat(url), {method: 'DELETE'});
    }

    post(url, payload){
        return fetch(apiUrl.concat(url), {
            method: 'POST',
            headers:{
                Accept: 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(payload)
        });
    }

    put(url, payload){
        return fetch(apiUrl.concat(url), {
            method: 'PUT',
            headers:{
                Accept: 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(payload)
        });
    }
}