import * as React from "react";
import Box from "@mui/material/Box";
import Paper from "@mui/material/Paper";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from '@mui/material/TableCell';
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import Card from "@mui/material/Card";
import Typography from "@mui/material/Typography";
import CardActions from "@mui/material/CardActions";
import Button from "@mui/material/Button";
import Modal from '@mui/material/Modal';
import LinearProgress from '@mui/material/LinearProgress';

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

const columns = [
  { id: "name", label: "품명", minWidth: 140 },
  { id: "price", label: "단가", minWidth: 50 },
  {
    id: "cnt",
    label: "수량",
    minWidth: 70,
    align: "right",
  },
  {
    id: "fullprice",
    label: "총액",
    minWidth: 70,
    align: "right",
  },
];

function createData(name, price, cnt) {
  const fullprice = price * cnt;
  return { name, price, cnt, fullprice };
}

const rows = [
  createData("폰트크기 바꿧지롱", 1500, 5),
  createData("동원참치", 4000, 1),
  createData("???", 10000, 3),
  createData("????", 5000, 4),
  createData("?????", 4000, 1),
  createData("??????", 5000, 2),
  createData("??", 12000, 5),
  createData("???", 8000, 6),
  createData("????", 12000, 2),
  createData("?????", 400, 3),
  createData("??????", 2400, 4),
  createData("???????", 500, 5),
];

const lstStyle = {
  fontSize: "24px",
}

export default function ItemList() {
  var paymentAll = 0;
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
          <h1 id="child-modal-title">인식중</h1>
          <p id="child-modal-description">
            결제하려면 카트를 RFID태그에 갖다대세요
          </p>
          <LinearProgress />
          <Button onClick={handleClose} sx={{ mt:2, width:'100%', fontWeight:'bold '}}>인식 취소</Button>
        </Box>
      </Modal>
      <Typography component="h1" variant="h3" sx={{ mb: 3, fontWeight:'bold' }}>
        장바구니
      </Typography>
      <Card sx={{ width: `90vw`, border: 1 }}>
        <Paper sx={{ width: "100%", overflow: "hidden" }}>
          <TableContainer sx={{ minHeight: `50vh`, maxHeight: `50vh` }}>
            <Table stickyHeader aria-label="sticky table" >
              <TableHead>
                <TableRow>
                  {columns.map((column) => (
                    <TableCell
                      key={column.id}
                      align={column.align}
                      style={lstStyle}
                      sx={{backgroundColor:'#90caf9', fontWeight:'bold'}}
                    >
                      {column.label}
                    </TableCell>
                  ))}
                </TableRow>
              </TableHead>
              <TableBody>
                {rows.map((row) => {
                  paymentAll = row.fullprice + paymentAll;
                  return (
                    <TableRow
                      hover
                      role="checkbox"
                      tabIndex={-1}
                      key={row.code}
                    >
                      {columns.map((column) => {
                        const value = row[column.id];
                        return (
                          <TableCell key={column.id} align={column.align} style={lstStyle} sx={{fontWeight:'bold'}} >
                            {column.format && typeof value === "number"
                              ? column.format(value)
                              : value}
                          </TableCell>
                        );
                      })}
                    </TableRow>
                  );
                })}
              </TableBody>
            </Table>
          </TableContainer>
          <Box sx={{borderTop:1}}>
          <Typography sx={{fontSize:33, fontWeight:'bold', color:'red', mx:4}}>
            다음 상품은 바코드로 결제해주세요
          </Typography>
          </Box>
          <CardActions sx={{borderTop:1}}>
            <Typography sx={{fontSize:35, fontWeight:'bold', margin:1}}>
              총 결제금액
            </Typography>
            <Typography sx={{position:"absolute", right:'6%',fontSize:30, fontWeight:'bold'}}>
              {paymentAll}원
            </Typography>
          </CardActions>
        </Paper>
        <Button color="error" variant="contained" sx={{fontSize:30, margin:1}} onClick={handleOpen}>물품 다시찍기</Button>
        <Button variant="contained" sx={{position:"absolute", right:'5%',fontSize:30, fontWeight:'bold', margin:1}}>결제하기</Button>
      </Card>
    </Box>
  );
}
