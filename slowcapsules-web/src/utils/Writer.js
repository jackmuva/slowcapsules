import WriterApi from "../api/WriterApi";


class Writer {
    static writer;

    constructor(writerId, penName, email){
        this.writerId = writerId;
        this.penName = penName;
        this.email = email;
    }

    init(){
        return WriterApi.getLoggedInWriter().then(response => {
            const writerId = response.data.writerId;
            const penName = response.data.penName;
            const email = response.data.email;
            this.writer = new Writer(writerId, penName, email);
        });
    }

    getWriter(){
        return this.writer;
    }
}