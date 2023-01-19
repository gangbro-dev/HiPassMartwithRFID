import * as React from "react";
import Card from "@mui/material/Card";
import CardActions from "@mui/material/CardActions";
import CardMedia from "@mui/material/CardMedia";
import Button from "@mui/material/Button";
import Box from "@mui/material/Box";
import styled from "styled-components";
import Modal from '@mui/material/Modal';

const Buttons = styled(Button)`
  width: 100%;
  font-weight: 700 !important;
`;

const style = {
  position: 'absolute',
  top: '50%',
  left: '50%',
  transform: 'translate(-50%, -50%)',
  width: 400,
  bgcolor: 'background.paper',
  border: '2px solid #000',
  boxShadow: 24,
  pt: 2,
  px: 4,
  pb: 3,
};

export default function ImgMediaCard() {
  const [open, setOpen] = React.useState(false);
  const handleOpen = () => {
    setOpen(true);
  };
  const handleClose = () => {
    setOpen(false);
  };

  return (
    <Box
      sx={{
        marginTop: 2,
        display: "flex",
        flexDirection: "column",
        alignItems: "center",
      }}
    >
      <Modal
        hideBackdrop
        open={open}
        onClose={handleClose}
        aria-labelledby="child-modal-title"
        aria-describedby="child-modal-description"
      >
        <Box sx={{ ...style, width: 400 }}>
          <h2 id="child-modal-title">인식중</h2>
          <p id="child-modal-description">
            결제하려면 카트를 RFID태그에 갖다대세요
          </p>
          <Buttons onClick={handleClose}>결제 취소</Buttons>
        </Box>
      </Modal>
      <Button onClick={handleOpen}>
        <Card sx={{ maxWidth: 600, minHeight: 1240}}>
          <CardMedia
            component="img"
            alt="howtowuse"
            height="1100"
            image="./images/howtouse.jpg"
          />
          <CardActions>
            <Buttons size="large">결제하려면 화면을 터치하세요</Buttons>
          </CardActions>
        </Card>
      </Button>
    </Box>
  );
}
