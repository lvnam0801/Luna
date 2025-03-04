import { Card } from "@/components/ui/card";
import { ClipboardCheck, Package, Truck, CheckCircle2 } from "lucide-react";
import { Button } from "@/components/ui/button";
import {
  Dialog,
  DialogContent,
  DialogHeader,
  DialogTitle,
  DialogScrollArea,
} from "@/components/ui/dialog";
import { useState } from "react";
import { ScrollArea } from "@/components/ui/scroll-area";

interface ImportReceipt {
  id: string;
  batchId: string;
  importDate: string;
  importer: string;
  productCount: number;
  totalValue: string;
  status: 'confirmed' | 'packed' | 'transfering' | 'completed';
  warehouse: string;
  products?: Array<{
    name: string;
    quantity: number;
    price: string;
  }>;
}

interface ImportReceiptListProps {
  searchQuery: string;
  filters: {
    warehouse: string;
    status: string;
  };
  isCreateDialogOpen: boolean;
  onCreateDialogChange: (open: boolean) => void;
}

const mockReceipts: ImportReceipt[] = [
  {
    id: '2906469',
    batchId: '7C202',
    importDate: '2024-10-10',
    importer: 'Trần Hoàng Minh',
    productCount: 125,
    totalValue: '45 M',
    status: 'transfering',
    warehouse: 'hcm',
    products: [
      { name: "Nike Air Max 270", quantity: 50, price: "20 M" },
      { name: "Adidas Ultraboost", quantity: 75, price: "25 M" }
    ]
  },
  {
    id: '2906468',
    batchId: '7C203',
    importDate: '2024-09-10',
    importer: 'Trần Hoàng Minh',
    productCount: 125,
    totalValue: '45 M',
    status: 'completed',
    warehouse: 'hn',
    products: [
      { name: "Nike Air Max 270", quantity: 50, price: "20 M" },
      { name: "Adidas Ultraboost", quantity: 75, price: "25 M" }
    ]
  },
  {
    id: '2906467',
    batchId: '7C201',
    importDate: '2024-08-15',
    importer: 'Nguyễn Văn An',
    productCount: 200,
    totalValue: '60 M',
    status: 'packed',
    warehouse: 'dn',
    products: [
      { name: "Puma RS-X", quantity: 100, price: "30 M" },
      { name: "Nike Air Force 1", quantity: 100, price: "30 M" }
    ]
  },
  {
    id: '2906466',
    batchId: '7C200',
    importDate: '2024-08-10',
    importer: 'Lê Thị Bình',
    productCount: 150,
    totalValue: '40 M',
    status: 'confirmed',
    warehouse: 'hcm',
    products: [
      { name: "Adidas NMD", quantity: 75, price: "20 M" },
      { name: "Nike React", quantity: 75, price: "20 M" }
    ]
  }
];

function StatusBar({ status }: { status: ImportReceipt['status'] }) {
  const steps = [
    { id: 'confirmed', icon: ClipboardCheck, label: 'Confirmed' },
    { id: 'packed', icon: Package, label: 'Packed' },
    { id: 'transfering', icon: Truck, label: 'Transfering' },
    { id: 'completed', icon: CheckCircle2, label: 'Completed' }
  ];

  const currentStep = steps.findIndex(step => step.id === status);

  return (
    <div className="flex items-center justify-between w-full mt-4 relative">
      {/* Base line */}
      <div className="absolute top-5 left-0 w-full h-[2px] bg-gray-200" />

      {steps.map((step, index) => (
        <div key={step.id} className="flex flex-col items-center relative">
          {/* Icon and label */}
          <div className={`${index <= currentStep ? 'text-blue-600' : 'text-gray-400'}`}>
            <div className={`w-10 h-10 rounded-full flex items-center justify-center z-10
              ${index <= currentStep ? 'bg-blue-100' : 'bg-gray-100'}`}>
              <step.icon className={`w-6 h-6`} />
            </div>
            <span className="text-xs mt-1">{step.label}</span>
          </div>

          {/* Colored line between steps */}
          {index < steps.length - 1 && (
            <div
              className={`absolute top-5 left-[40px] w-[calc(200% - 40px)] h-[2px]
                ${index < currentStep ? 'bg-blue-600' : 'bg-gray-200'}`}
            />
          )}
        </div>
      ))}
    </div>
  );
}

function ReceiptDetails({ receipt, open, onOpenChange }: {
  receipt: ImportReceipt;
  open: boolean;
  onOpenChange: (open: boolean) => void;
}) {
  return (
    <Dialog open={open} onOpenChange={onOpenChange}>
      <DialogContent className="max-w-2xl">
        <DialogHeader>
          <DialogTitle>Chi tiết phiếu nhập #{receipt.id}</DialogTitle>
        </DialogHeader>
        <div className="space-y-4">
          <div className="grid grid-cols-2 gap-4">
            <div>
              <div className="text-sm text-muted-foreground">Mã lô hàng</div>
              <div className="font-medium">{receipt.batchId}</div>
            </div>
            <div>
              <div className="text-sm text-muted-foreground">Ngày nhập</div>
              <div className="font-medium">{receipt.importDate}</div>
            </div>
            <div>
              <div className="text-sm text-muted-foreground">Người nhập</div>
              <div className="font-medium">{receipt.importer}</div>
            </div>
            <div>
              <div className="text-sm text-muted-foreground">Kho</div>
              <div className="font-medium">{receipt.warehouse}</div>
            </div>
          </div>

          <div className="pt-4">
            <div className="text-sm font-medium mb-2">Danh sách sản phẩm</div>
            <table className="w-full">
              <thead>
                <tr className="text-sm text-muted-foreground border-b">
                  <th className="text-left py-2">Sản phẩm</th>
                  <th className="text-right py-2">Số lượng</th>
                  <th className="text-right py-2">Giá trị</th>
                </tr>
              </thead>
              <tbody>
                {receipt.products?.map((product, index) => (
                  <tr key={index} className="border-b">
                    <td className="py-2">{product.name}</td>
                    <td className="text-right py-2">{product.quantity}</td>
                    <td className="text-right py-2">{product.price}</td>
                  </tr>
                ))}
                <tr className="font-medium">
                  <td className="py-2">Tổng cộng</td>
                  <td className="text-right py-2">{receipt.productCount}</td>
                  <td className="text-right py-2">{receipt.totalValue}</td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </DialogContent>
    </Dialog>
  );
}

interface CreateReceiptProps {
  open: boolean;
  onOpenChange: (open: boolean) => void;
  onSubmit: (receipt: Omit<ImportReceipt, 'id' | 'status'>) => void;
}

function CreateReceipt({ open, onOpenChange, onSubmit }: CreateReceiptProps) {
  const [formData, setFormData] = useState({
    batchId: '',
    importDate: new Date().toISOString().split('T')[0],
    importer: '',
    warehouse: 'hcm',
    products: [{
      name: '',
      quantity: 0,
      price: ''
    }]
  });

  const handleSubmit = () => {
    // Validate form data
    if (!formData.batchId || !formData.importer || !formData.products.some(p => p.name && p.quantity > 0)) {
      return;
    }

    // Calculate totals
    const productCount = formData.products.reduce((sum, p) => sum + (p.quantity || 0), 0);
    const totalValue = formData.products.reduce((sum, p) => {
      const price = parseInt(p.price?.replace(/[^0-9]/g, '') || '0');
      return sum + price;
    }, 0) + ' M';

    // Create new receipt and update list
    const receipt = {
      ...formData,
      productCount,
      totalValue
    };
    onSubmit(receipt);
    onOpenChange(false);

    // Reset form
    setFormData({
      batchId: '',
      importDate: new Date().toISOString().split('T')[0],
      importer: '',
      warehouse: 'hcm',
      products: [{
        name: '',
        quantity: 0,
        price: ''
      }]
    });
  };

  const addProduct = () => {
    setFormData(prev => ({
      ...prev,
      products: [...prev.products, { name: '', quantity: 0, price: '' }]
    }));
  };

  const updateProduct = (index: number, field: keyof typeof formData.products[0], value: string | number) => {
    setFormData(prev => ({
      ...prev,
      products: prev.products.map((p, i) =>
        i === index ? { ...p, [field]: value } : p
      )
    }));
  };

  return (
    <Dialog open={open} onOpenChange={onOpenChange}>
      <DialogContent className="max-w-2xl">
        <DialogHeader>
          <DialogTitle>Tạo phiếu nhập mới</DialogTitle>
        </DialogHeader>
        <DialogScrollArea className="max-h-[80vh]">
          <div className="space-y-4 p-1">
            <div className="grid grid-cols-2 gap-4">
              <div>
                <label className="text-sm text-muted-foreground">Mã lô hàng</label>
                <input
                  type="text"
                  className="w-full p-2 border rounded"
                  value={formData.batchId}
                  onChange={e => setFormData(prev => ({ ...prev, batchId: e.target.value }))}
                />
              </div>
              <div>
                <label className="text-sm text-muted-foreground">Ngày nhập</label>
                <input
                  type="date"
                  className="w-full p-2 border rounded"
                  value={formData.importDate}
                  onChange={e => setFormData(prev => ({ ...prev, importDate: e.target.value }))}
                />
              </div>
              <div>
                <label className="text-sm text-muted-foreground">Người nhập</label>
                <input
                  type="text"
                  className="w-full p-2 border rounded"
                  value={formData.importer}
                  onChange={e => setFormData(prev => ({ ...prev, importer: e.target.value }))}
                />
              </div>
              <div>
                <label className="text-sm text-muted-foreground">Kho</label>
                <select
                  className="w-full p-2 border rounded"
                  value={formData.warehouse}
                  onChange={e => setFormData(prev => ({ ...prev, warehouse: e.target.value }))}
                >
                  <option value="hcm">Kho HCM</option>
                  <option value="hn">Kho HN</option>
                  <option value="dn">Kho DN</option>
                </select>
              </div>
            </div>

            <div className="pt-4">
              <div className="flex justify-between items-center mb-2">
                <div className="text-sm font-medium">Danh sách sản phẩm</div>
                <Button onClick={addProduct} variant="outline" size="sm">
                  Thêm sản phẩm
                </Button>
              </div>
              <ScrollArea className="max-h-[400px]">
                <table className="w-full">
                  <thead className="sticky top-0 bg-white">
                    <tr className="text-sm text-muted-foreground border-b">
                      <th className="text-left py-2">Sản phẩm</th>
                      <th className="text-right py-2">Số lượng</th>
                      <th className="text-right py-2">Giá trị (M)</th>
                    </tr>
                  </thead>
                  <tbody>
                    {formData.products.map((product, index) => (
                      <tr key={index} className="border-b">
                        <td className="py-2">
                          <input
                            type="text"
                            className="w-full p-1 border rounded"
                            value={product.name}
                            onChange={e => updateProduct(index, 'name', e.target.value)}
                          />
                        </td>
                        <td className="py-2">
                          <input
                            type="number"
                            className="w-full p-1 border rounded text-right"
                            value={product.quantity}
                            onChange={e => updateProduct(index, 'quantity', parseInt(e.target.value) || 0)}
                          />
                        </td>
                        <td className="py-2">
                          <input
                            type="text"
                            className="w-full p-1 border rounded text-right"
                            value={product.price}
                            onChange={e => {
                              const value = e.target.value.replace(/[^0-9]/g, '');
                              updateProduct(index, 'price', value ? value + ' M' : '');
                            }}
                          />
                        </td>
                      </tr>
                    ))}
                  </tbody>
                </table>
              </ScrollArea>
            </div>
          </div>
        </DialogScrollArea>
        <div className="flex justify-end gap-2 pt-4">
          <Button variant="outline" onClick={() => onOpenChange(false)}>
            Hủy
          </Button>
          <Button onClick={handleSubmit}>
            Tạo phiếu nhập
          </Button>
        </div>
      </DialogContent>
    </Dialog>
  );
}

interface ImportReceiptListProps {
  searchQuery: string;
  filters: {
    warehouse: string;
    status: string;
  };
  isCreateDialogOpen: boolean;
  onCreateDialogChange: (open: boolean) => void;
}

export default function ImportReceiptList({
  searchQuery,
  filters,
  isCreateDialogOpen,
  onCreateDialogChange
}: ImportReceiptListProps) {
  const [selectedReceipt, setSelectedReceipt] = useState<ImportReceipt | null>(null);
  const [receipts, setReceipts] = useState(mockReceipts);

  // Filter receipts based on search query and filters
  const filteredReceipts = receipts.filter(receipt => {
    // Search filter
    if (searchQuery && !receipt.id.toLowerCase().includes(searchQuery.toLowerCase())) {
      return false;
    }

    // Warehouse filter
    if (filters.warehouse !== 'all' && receipt.warehouse !== filters.warehouse) {
      return false;
    }

    // Status filter
    if (filters.status !== 'all' && receipt.status !== filters.status) {
      return false;
    }

    return true;
  });

  const handleCreateReceipt = (newReceipt: Omit<ImportReceipt, 'id' | 'status'>) => {
    const receipt: ImportReceipt = {
      ...newReceipt,
      id: (parseInt(receipts[0].id) + 1).toString(),
      status: 'confirmed'
    };

    setReceipts([receipt, ...receipts]);
  };

  return (
    <div className="space-y-4">
      {filteredReceipts.map((receipt) => (
        <Card key={receipt.id} className="p-4">
          <div className="flex justify-between items-start">
            <div className="space-y-4 w-full">
              <div className="flex justify-between items-start">
                <div>
                  <div className="flex items-center gap-4">
                    <div>
                      <div className="text-lg font-medium">Mã phiếu: {receipt.id}</div>
                      <div className="text-sm text-muted-foreground">Mã lô hàng: {receipt.batchId}</div>
                    </div>
                  </div>
                  <div className="grid grid-cols-2 gap-x-8 gap-y-2 mt-2">
                    <div className="text-sm text-muted-foreground">Ngày nhập: {receipt.importDate}</div>
                    <div className="text-sm text-muted-foreground">Số lượng sản phẩm: {receipt.productCount}</div>
                    <div className="text-sm text-muted-foreground">Người nhập: {receipt.importer}</div>
                    <div className="text-sm text-muted-foreground">Tổng giá trị: {receipt.totalValue}</div>
                    <div className="text-sm text-muted-foreground">Kho: {receipt.warehouse}</div>
                  </div>
                </div>
                <Button
                  variant="outline"
                  onClick={() => setSelectedReceipt(receipt)}
                >
                  Chi tiết
                </Button>
              </div>
              <div className="border-t pt-4">
                <StatusBar status={receipt.status} />
              </div>
            </div>
          </div>
        </Card>
      ))}

      {selectedReceipt && (
        <ReceiptDetails
          receipt={selectedReceipt}
          open={!!selectedReceipt}
          onOpenChange={(open) => !open && setSelectedReceipt(null)}
        />
      )}

      <CreateReceipt
        open={isCreateDialogOpen}
        onOpenChange={onCreateDialogChange}
        onSubmit={handleCreateReceipt}
      />
    </div>
  );
}

export { CreateReceipt };