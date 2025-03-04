import { Card } from "@/components/ui/card";
import { Tabs, TabsContent, TabsList, TabsTrigger } from "@/components/ui/tabs";
import { LineChart, Line, XAxis, YAxis, CartesianGrid, Tooltip, ResponsiveContainer } from 'recharts';
import { TrendingUp, ArrowRightLeft, Package } from "lucide-react";

const mockTrendData = [
  { month: 'Jan', sales: 400 },
  { month: 'Feb', sales: 300 },
  { month: 'Mar', sales: 600 },
  { month: 'Apr', sales: 800 },
  { month: 'May', sales: 700 }
];

const ProductRecommendation = () => (
  <div className="space-y-4">
    <h3 className="text-lg font-medium mb-4">Đề xuất nhập hàng</h3>
    <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
      <Card className="p-4">
        <div className="flex items-start gap-4">
          <div className="w-12 h-12 bg-blue-100 rounded-lg flex items-center justify-center">
            <TrendingUp className="h-6 w-6 text-blue-600" />
          </div>
          <div>
            <h4 className="font-medium">Nike Air Max 270</h4>
            <p className="text-sm text-muted-foreground">Tồn kho thấp, xu hướng tăng</p>
            <p className="text-sm font-medium mt-2">Đề xuất: Nhập thêm 200 đơn vị</p>
          </div>
        </div>
        <div className="mt-4 h-[100px]">
          <ResponsiveContainer width="100%" height="100%">
            <LineChart data={mockTrendData}>
              <CartesianGrid strokeDasharray="3 3" />
              <XAxis dataKey="month" />
              <YAxis />
              <Tooltip />
              <Line type="monotone" dataKey="sales" stroke="#2563eb" />
            </LineChart>
          </ResponsiveContainer>
        </div>
      </Card>

      <Card className="p-4">
        <div className="flex items-start gap-4">
          <div className="w-12 h-12 bg-green-100 rounded-lg flex items-center justify-center">
            <Package className="h-6 w-6 text-green-600" />
          </div>
          <div>
            <h4 className="font-medium">Adidas Ultraboost</h4>
            <p className="text-sm text-muted-foreground">Sắp hết hàng</p>
            <p className="text-sm font-medium mt-2">Đề xuất: Nhập thêm 150 đơn vị</p>
          </div>
        </div>
        <div className="mt-4 h-[100px]">
          <ResponsiveContainer width="100%" height="100%">
            <LineChart data={mockTrendData}>
              <CartesianGrid strokeDasharray="3 3" />
              <XAxis dataKey="month" />
              <YAxis />
              <Tooltip />
              <Line type="monotone" dataKey="sales" stroke="#16a34a" />
            </LineChart>
          </ResponsiveContainer>
        </div>
      </Card>
    </div>
  </div>
);

const TransferRecommendation = () => (
  <div className="space-y-4">
    <h3 className="text-lg font-medium mb-4">Đề xuất điều chuyển hàng</h3>
    <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
      <Card className="p-4">
        <div className="flex items-start gap-4">
          <div className="w-12 h-12 bg-orange-100 rounded-lg flex items-center justify-center">
            <ArrowRightLeft className="h-6 w-6 text-orange-600" />
          </div>
          <div>
            <h4 className="font-medium">Kho HCM → Kho HN</h4>
            <p className="text-sm text-muted-foreground">Tối ưu hóa tồn kho</p>
            <p className="text-sm font-medium mt-2">Đề xuất: Chuyển 100 đơn vị Nike Air Max</p>
          </div>
        </div>
      </Card>

      <Card className="p-4">
        <div className="flex items-start gap-4">
          <div className="w-12 h-12 bg-purple-100 rounded-lg flex items-center justify-center">
            <ArrowRightLeft className="h-6 w-6 text-purple-600" />
          </div>
          <div>
            <h4 className="font-medium">Kho HN → Kho DN</h4>
            <p className="text-sm text-muted-foreground">Đáp ứng nhu cầu địa phương</p>
            <p className="text-sm font-medium mt-2">Đề xuất: Chuyển 50 đơn vị Adidas Ultraboost</p>
          </div>
        </div>
      </Card>
    </div>
  </div>
);

export default function Planning() {
  return (
    <main className="container mx-auto px-4 py-6">
      <div className="flex justify-between items-center mb-6">
        <h1 className="text-2xl font-bold">Kế hoạch</h1>
      </div>

      <Card className="p-6">
        <Tabs defaultValue="products">
          <TabsList className="grid w-full grid-cols-2 mb-6">
            <TabsTrigger value="products">Đề xuất nhập hàng</TabsTrigger>
            <TabsTrigger value="transfers">Đề xuất điều chuyển</TabsTrigger>
          </TabsList>
          <TabsContent value="products">
            <ProductRecommendation />
          </TabsContent>
          <TabsContent value="transfers">
            <TransferRecommendation />
          </TabsContent>
        </Tabs>
      </Card>
    </main>
  );
}
