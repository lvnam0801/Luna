import {
  Dialog,
  DialogContent,
  DialogHeader,
  DialogTitle,
} from "@/components/ui/dialog";
import { Button } from "@/components/ui/button";
import { useState } from "react";

interface CreateProductProps {
  open: boolean;
  onOpenChange: (open: boolean) => void;
  onSubmit: (product: {
    name: string;
    sku: string;
    location: string;
    expiryDate: string;
    retailPrice: string;
    wholesalePrice: string;
    status: string;
    category: string;
  }) => void;
}

export default function CreateProductDialog({ open, onOpenChange, onSubmit }: CreateProductProps) {
  const [formData, setFormData] = useState({
    name: '',
    sku: '',
    location: 'HCM',
    expiryDate: new Date().toISOString().split('T')[0],
    retailPrice: '',
    wholesalePrice: '',
    status: 'active',
    category: 'shoes'
  });

  const handleSubmit = () => {
    // Validate form data
    if (!formData.name || !formData.sku) {
      return;
    }

    onSubmit(formData);
    onOpenChange(false);

    // Reset form
    setFormData({
      name: '',
      sku: '',
      location: 'HCM',
      expiryDate: new Date().toISOString().split('T')[0],
      retailPrice: '',
      wholesalePrice: '',
      status: 'active',
      category: 'shoes'
    });
  };

  return (
    <Dialog open={open} onOpenChange={onOpenChange}>
      <DialogContent className="max-w-2xl">
        <DialogHeader>
          <DialogTitle>Thêm sản phẩm mới</DialogTitle>
        </DialogHeader>
        <div className="space-y-4">
          <div className="grid grid-cols-2 gap-4">
            <div>
              <label className="text-sm text-muted-foreground">Tên sản phẩm</label>
              <input
                type="text"
                className="w-full p-2 border rounded"
                value={formData.name}
                onChange={e => setFormData(prev => ({ ...prev, name: e.target.value }))}
              />
            </div>
            <div>
              <label className="text-sm text-muted-foreground">Mã SKU</label>
              <input
                type="text"
                className="w-full p-2 border rounded"
                value={formData.sku}
                onChange={e => setFormData(prev => ({ ...prev, sku: e.target.value }))}
              />
            </div>
            <div>
              <label className="text-sm text-muted-foreground">Kho</label>
              <select
                className="w-full p-2 border rounded"
                value={formData.location}
                onChange={e => setFormData(prev => ({ ...prev, location: e.target.value }))}
              >
                <option value="HCM">Kho HCM</option>
                <option value="HN">Kho HN</option>
                <option value="DN">Kho DN</option>
              </select>
            </div>
            <div>
              <label className="text-sm text-muted-foreground">Ngày hết hạn</label>
              <input
                type="date"
                className="w-full p-2 border rounded"
                value={formData.expiryDate}
                onChange={e => setFormData(prev => ({ ...prev, expiryDate: e.target.value }))}
              />
            </div>
            <div>
              <label className="text-sm text-muted-foreground">Giá bán lẻ</label>
              <input
                type="text"
                className="w-full p-2 border rounded"
                value={formData.retailPrice}
                onChange={e => setFormData(prev => ({ ...prev, retailPrice: e.target.value + ' VND' }))}
              />
            </div>
            <div>
              <label className="text-sm text-muted-foreground">Giá bán sỉ</label>
              <input
                type="text"
                className="w-full p-2 border rounded"
                value={formData.wholesalePrice}
                onChange={e => setFormData(prev => ({ ...prev, wholesalePrice: e.target.value + ' VND' }))}
              />
            </div>
            <div>
              <label className="text-sm text-muted-foreground">Trạng thái</label>
              <select
                className="w-full p-2 border rounded"
                value={formData.status}
                onChange={e => setFormData(prev => ({ ...prev, status: e.target.value }))}
              >
                <option value="active">Đang bán</option>
                <option value="inactive">Ngừng bán</option>
              </select>
            </div>
            <div>
              <label className="text-sm text-muted-foreground">Danh mục</label>
              <select
                className="w-full p-2 border rounded"
                value={formData.category}
                onChange={e => setFormData(prev => ({ ...prev, category: e.target.value }))}
              >
                <option value="shoes">Giày dép</option>
                <option value="clothing">Quần áo</option>
                <option value="accessories">Phụ kiện</option>
              </select>
            </div>
          </div>

          <div className="flex justify-end gap-2 pt-4">
            <Button variant="outline" onClick={() => onOpenChange(false)}>
              Hủy
            </Button>
            <Button onClick={handleSubmit}>
              Thêm sản phẩm
            </Button>
          </div>
        </div>
      </DialogContent>
    </Dialog>
  );
}
