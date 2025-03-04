import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table";
import { Button } from "@/components/ui/button";
import { MoreHorizontal } from "lucide-react";
import { mockProducts } from "@/lib/mockData";
import { useState } from "react";
import CreateProductDialog from "./CreateProductDialog";

interface ProductTableProps {
  searchQuery: string;
  filters: {
    status: string;
    category: string;
    price: string;
  };
  isCreateDialogOpen: boolean;
  onCreateDialogChange: (open: boolean) => void;
}

export default function ProductTable({ 
  searchQuery, 
  filters,
  isCreateDialogOpen,
  onCreateDialogChange
}: ProductTableProps) {
  const [products, setProducts] = useState(mockProducts);

  // Filter products based on search query and filters
  const filteredProducts = products.filter(product => {
    // Search filter
    if (searchQuery) {
      const searchLower = searchQuery.toLowerCase();
      if (!product.name.toLowerCase().includes(searchLower) && 
          !product.sku.toLowerCase().includes(searchLower)) {
        return false;
      }
    }

    // Status filter
    if (filters.status !== 'all') {
      if (filters.status === 'active' && product.status !== 'active') return false;
      if (filters.status === 'inactive' && product.status !== 'inactive') return false;
    }

    // Category filter
    if (filters.category !== 'all' && product.category !== filters.category) {
      return false;
    }

    // Price filter
    if (filters.price !== 'all') {
      const price = parseFloat(product.retailPrice.replace(/[^0-9.-]+/g, ""));
      switch (filters.price) {
        case '0-100':
          if (price > 100000) return false;
          break;
        case '100-300':
          if (price < 100000 || price > 300000) return false;
          break;
        case '300+':
          if (price < 300000) return false;
          break;
      }
    }

    return true;
  });

  const handleCreateProduct = (newProduct: any) => {
    const product = {
      ...newProduct,
      id: products.length + 1
    };
    setProducts([product, ...products]);
  };

  return (
    <div className="bg-white rounded-lg border">
      <Table>
        <TableHeader>
          <TableRow>
            <TableHead className="w-[50px]"></TableHead>
            <TableHead>Sản phẩm</TableHead>
            <TableHead>Kho</TableHead>
            <TableHead>Hạn sử dụng</TableHead>
            <TableHead>Giá bán lẻ</TableHead>
            <TableHead>Giá bán sỉ</TableHead>
            <TableHead className="text-right">Actions</TableHead>
          </TableRow>
        </TableHeader>
        <TableBody>
          {filteredProducts.map((product) => (
            <TableRow key={product.id}>
              <TableCell>
                <div className="w-10 h-10 bg-gray-100 rounded flex items-center justify-center">
                  <div className="w-6 h-6 bg-gray-300 rounded" />
                </div>
              </TableCell>
              <TableCell>
                <div className="flex items-center gap-2">
                  <div 
                    className={`w-2 h-2 rounded-full ${
                      product.status === 'active' 
                        ? 'bg-blue-500' 
                        : 'bg-gray-400'
                    }`}
                  />
                  <div>
                    <div className="font-medium">{product.name}</div>
                    <div className="text-sm text-muted-foreground">SKU: {product.sku}</div>
                  </div>
                </div>
              </TableCell>
              <TableCell>{product.location}</TableCell>
              <TableCell>{product.expiryDate}</TableCell>
              <TableCell>{product.retailPrice}</TableCell>
              <TableCell>{product.wholesalePrice}</TableCell>
              <TableCell className="text-right">
                <Button variant="ghost" size="icon">
                  <MoreHorizontal className="h-4 w-4" />
                </Button>
              </TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>

      <CreateProductDialog
        open={isCreateDialogOpen}
        onOpenChange={onCreateDialogChange}
        onSubmit={handleCreateProduct}
      />
    </div>
  );
}