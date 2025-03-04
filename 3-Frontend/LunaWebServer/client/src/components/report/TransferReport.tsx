import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table";
import { Card } from "@/components/ui/card";

interface TransferItem {
  id: number;
  date: string;
  fromWarehouse: string;
  toWarehouse: string;
  productName: string;
  quantity: number;
  status: string;
}

const mockTransfers: TransferItem[] = [
  {
    id: 1,
    date: "2024-02-27",
    fromWarehouse: "Kho HCM",
    toWarehouse: "Kho HN",
    productName: "Nike Air Max 270",
    quantity: 50,
    status: "Completed"
  },
  {
    id: 2,
    date: "2024-02-26",
    fromWarehouse: "Kho HN",
    toWarehouse: "Kho DN",
    productName: "Adidas Ultraboost",
    quantity: 30,
    status: "In Transit"
  }
];

export default function TransferReport() {
  return (
    <div className="space-y-6">
      <div className="grid grid-cols-3 gap-4">
        <Card className="p-4">
          <div className="text-sm text-muted-foreground">Tổng số lần chuyển kho</div>
          <div className="text-2xl font-bold">24 lần</div>
        </Card>
        <Card className="p-4">
          <div className="text-sm text-muted-foreground">Số lượng hàng đã chuyển</div>
          <div className="text-2xl font-bold">1,240 sản phẩm</div>
        </Card>
        <Card className="p-4">
          <div className="text-sm text-muted-foreground">Tuyến đường phổ biến</div>
          <div className="text-2xl font-bold">HCM → HN</div>
        </Card>
      </div>

      <Table>
        <TableHeader>
          <TableRow>
            <TableHead>Ngày chuyển</TableHead>
            <TableHead>Từ kho</TableHead>
            <TableHead>Đến kho</TableHead>
            <TableHead>Sản phẩm</TableHead>
            <TableHead>Số lượng</TableHead>
            <TableHead>Trạng thái</TableHead>
          </TableRow>
        </TableHeader>
        <TableBody>
          {mockTransfers.map((item) => (
            <TableRow key={item.id}>
              <TableCell>{item.date}</TableCell>
              <TableCell>{item.fromWarehouse}</TableCell>
              <TableCell>{item.toWarehouse}</TableCell>
              <TableCell>{item.productName}</TableCell>
              <TableCell>{item.quantity}</TableCell>
              <TableCell>{item.status}</TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </div>
  );
}
