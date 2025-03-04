import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table";
import { Card } from "@/components/ui/card";
import { ResponsiveContainer, LineChart, Line, XAxis, YAxis, CartesianGrid, Tooltip } from 'recharts';

interface PurchaseItem {
  id: number;
  date: string;
  supplier: string;
  productName: string;
  quantity: number;
  totalCost: string;
  status: string;
}

const purchaseData = [
  { month: 'Jan', value: 4000 },
  { month: 'Feb', value: 3000 },
  { month: 'Mar', value: 6000 },
  { month: 'Apr', value: 2780 },
  { month: 'May', value: 1890 },
  { month: 'Jun', value: 2390 }
];

const mockPurchases: PurchaseItem[] = [
  {
    id: 1,
    date: "2024-02-27",
    supplier: "Nike Vietnam",
    productName: "Nike Air Max 270",
    quantity: 100,
    totalCost: "28M VND",
    status: "Delivered"
  },
  {
    id: 2,
    date: "2024-02-26",
    supplier: "Adidas Vietnam",
    productName: "Ultraboost",
    quantity: 75,
    totalCost: "21M VND",
    status: "Processing"
  }
];

export default function PurchasingReport() {
  return (
    <div className="space-y-6">
      <div className="grid grid-cols-3 gap-4">
        <Card className="p-4">
          <div className="text-sm text-muted-foreground">Tổng giá trị mua hàng</div>
          <div className="text-2xl font-bold">320M VND</div>
        </Card>
        <Card className="p-4">
          <div className="text-sm text-muted-foreground">Số lượng đơn hàng</div>
          <div className="text-2xl font-bold">45 đơn</div>
        </Card>
        <Card className="p-4">
          <div className="text-sm text-muted-foreground">Nhà cung cấp hàng đầu</div>
          <div className="text-2xl font-bold">Nike Vietnam</div>
        </Card>
      </div>

      <Card className="p-6">
        <h3 className="text-lg font-medium mb-4">Xu hướng mua hàng</h3>
        <div className="h-[300px]">
          <ResponsiveContainer width="100%" height="100%">
            <LineChart data={purchaseData}>
              <CartesianGrid strokeDasharray="3 3" />
              <XAxis dataKey="month" />
              <YAxis />
              <Tooltip />
              <Line type="monotone" dataKey="value" stroke="#8884d8" />
            </LineChart>
          </ResponsiveContainer>
        </div>
      </Card>

      <Table>
        <TableHeader>
          <TableRow>
            <TableHead>Ngày</TableHead>
            <TableHead>Nhà cung cấp</TableHead>
            <TableHead>Sản phẩm</TableHead>
            <TableHead>Số lượng</TableHead>
            <TableHead>Tổng giá trị</TableHead>
            <TableHead>Trạng thái</TableHead>
          </TableRow>
        </TableHeader>
        <TableBody>
          {mockPurchases.map((item) => (
            <TableRow key={item.id}>
              <TableCell>{item.date}</TableCell>
              <TableCell>{item.supplier}</TableCell>
              <TableCell>{item.productName}</TableCell>
              <TableCell>{item.quantity}</TableCell>
              <TableCell>{item.totalCost}</TableCell>
              <TableCell>{item.status}</TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </div>
  );
}
