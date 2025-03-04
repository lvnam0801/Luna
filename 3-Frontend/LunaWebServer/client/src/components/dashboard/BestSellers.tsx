import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import { mockBestSellers } from "@/lib/mockData";
import { Package2 } from "lucide-react";

export default function BestSellers() {
  return (
    <Card>
      <CardHeader>
        <CardTitle>Best Selling Products</CardTitle>
      </CardHeader>
      <CardContent className="space-y-4">
        {mockBestSellers.map((product) => (
          <div key={product.id} className="flex items-center space-x-4">
            <div className="w-12 h-12 bg-muted rounded-lg flex items-center justify-center">
              <Package2 className="w-6 h-6 text-muted-foreground" />
            </div>
            <div className="flex-1">
              <div className="font-medium">{product.name}</div>
              <div className="text-sm text-muted-foreground">SKU: {product.sku}</div>
            </div>
            <div className="text-right">
              <div className="font-medium">{product.quantity}</div>
              <div className="text-sm text-muted-foreground">Units Sold</div>
            </div>
          </div>
        ))}
      </CardContent>
    </Card>
  );
}
