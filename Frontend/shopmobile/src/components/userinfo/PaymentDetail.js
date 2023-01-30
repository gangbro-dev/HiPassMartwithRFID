import * as React from "react";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import Paper from "@mui/material/Paper";
import { Box, Card, Grid } from "@mui/material";
import { TableVirtuoso } from "react-virtuoso";

const sample = [
  ["농심)신라면5개입", 1, 3100],
  ["오뚜기)진라면5개입", 1, 2800],
  ["해태)크라운산도", 1, 3000],
  ["오리온)초코파이", 1, 4000],
  ["롯데)아몬드빼빼로", 2, 1500],
];

function createData(id, itemName, count, price) {
  return { id, itemName, count, price };
}

const columns = [
  {
    width: 240,
    label: "품명",
    dataKey: "itemName",
  },
  {
    width: 80,
    label: "개수",
    dataKey: "count",
    numeric: true,
  },
  {
    width: 80,
    label: "단가",
    dataKey: "price",
    numeric: true,
  },
];

const rows = Array.from({ length: 5 }, (_, index) => {
  const randomSelection = sample[Math.floor(Math.random() * sample.length)];
  return createData(index, ...randomSelection);
});

const VirtuosoTableComponents = {
  Scroller: React.forwardRef((props, ref) => (
    <TableContainer component={Paper} {...props} ref={ref} />
  )),
  Table: (props) => <Table {...props} style={{ borderCollapse: "separate" }} />,
  TableHead,
  TableRow: ({ item: _item, ...props }) => <TableRow {...props} />,
  TableBody: React.forwardRef((props, ref) => (
    <TableBody {...props} ref={ref} />
  )),
};

function fixedHeaderContent() {
  return (
    <TableRow>
      {columns.map((column) => (
        <TableCell
          key={column.dataKey}
          variant="head"
          align={column.numeric || false ? "right" : "left"}
          style={{ width: column.width }}
          sx={{
            backgroundColor: "#90caf9",
            fontWeight: "bold",
            fontSize: 18,
            borderBottom: 1,
          }}
        >
          {column.label}
        </TableCell>
      ))}
    </TableRow>
  );
}

function rowContent(_index, row) {
  return (
    <React.Fragment>
      {columns.map((column) => (
        <TableCell
          key={column.dataKey}
          align={column.numeric || false ? "right" : "left"}
          sx={{fontWeight:'bold'}}
        >
          {row[column.dataKey]}
        </TableCell>
      ))}
    </React.Fragment>
  );
}

export default function Payment() {
  return (
    <Box>
      <Grid container spacing={2}>
        <Grid />
        <Grid item xs={12}>
          <Card sx={{ fontSize:33, padding:2, textAlign:"center",backgroundColor:'#64b5f6', fontWeight:'bold'}}>상세결제내역</Card>
          <Paper style={{ height: "85vh", width: "100%" }}>
            <TableVirtuoso
              data={rows}
              components={VirtuosoTableComponents}
              fixedHeaderContent={fixedHeaderContent}
              itemContent={rowContent}
            />
          </Paper>
        </Grid>
      </Grid>
    </Box>
  );
}
