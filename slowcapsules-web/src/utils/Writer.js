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
            const writerId = response[0].writerId;
            const penName = response[0].penName;
            const email = response[0].email;
            this.writer = new Writer(writerId, penName, email);
        });
    }

    getWriter(){
        return this.writer;
    }
}