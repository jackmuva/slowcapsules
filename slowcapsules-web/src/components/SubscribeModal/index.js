import React, { useState } from 'react';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogTitle from '@mui/material/DialogTitle';
import Button from '@mui/material/Button';
import IconButton from '@mui/material/IconButton';
import EditIcon from '@mui/icons-material/Edit';
import TextField from '@mui/material/TextField';
import Stack from '@mui/material/Stack';
import SubscriptionApi from "../../api/SubscriptionApi";

function SubscribeModal(props) {
    const [open, setOpen] = useState(false);
    const [subscription, setSubscription] = useState({
        subscriberEmail: '',
        articleNum: '',
        sendDate: '',
        seriesId:''
    });

    // Open the modal form and update the car state
    const handleClickOpen = () => {
        let dateString = new Date().toISOString().slice(0, 10);
        setSubscription({
            subscriberEmail: '',
            articleNum: 1,
            sendDate: dateString,
            seriesId: props.series.seriesId
        })
        setOpen(true);
    }

    // Close the modal form
    const handleClose = () => {
        setOpen(false);
    };

    const handleChange = (event) => {
        setSubscription({...subscription,
            [event.target.name]: event.target.value});
    }

    // Update car and close modal form
    const handleSave = () => {
        SubscriptionApi.postNewSubscription(subscription);
        handleClose();
    }

    return(
        <div>
            <IconButton onClick={handleClickOpen}>
                <EditIcon color="primary" />
            </IconButton>
            <Dialog open={open} onClose={handleClose}>
                <DialogTitle>Subscribe</DialogTitle>
                <DialogContent>
                    <Stack spacing={2} mt={1}>
                        <TextField label="Email" name="subscriberEmail" autoFocus
                                   variant="standard" value={subscription.subscriberEmail}
                                   onChange={handleChange}/>
                    </Stack>
                </DialogContent>
                <DialogActions>
                    <Button onClick={handleClose}>Cancel</Button>
                    <Button onClick={handleSave}>Save</Button>
                </DialogActions>
            </Dialog>
        </div>
    );
}

export default SubscribeModal;