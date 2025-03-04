import {
  Dialog,
  DialogContent,
  DialogHeader,
  DialogTitle,
} from "@/components/ui/dialog";
import { Button } from "@/components/ui/button";
import { useState } from "react";

interface CreateWarehouseProps {
  open: boolean;
  onOpenChange: (open: boolean) => void;
  onSubmit: (warehouse: {
    name: string;
    phone: string;
    email: string;
    address: string;
  }) => void;
}

export default function CreateWarehouseDialog({ open, onOpenChange, onSubmit }: CreateWarehouseProps) {
  const [formData, setFormData] = useState({
    name: '',
    phone: '',
    email: '',
    address: ''
  });

  const handleSubmit = () => {
    // Validate form data
    if (!formData.name || !formData.address) {
      return;
    }

    onSubmit(formData);
    onOpenChange(false);

    // Reset form
    setFormData({
      name: '',
      phone: '',
      email: '',
      address: ''
    });
  };

  return (
    <Dialog open={open} onOpenChange={onOpenChange}>
      <DialogContent className="max-w-lg">
        <DialogHeader>
          <DialogTitle>Thêm kho mới</DialogTitle>
        </DialogHeader>
        <div className="space-y-4">
          <div>
            <label className="text-sm text-muted-foreground">Tên kho</label>
            <input
              type="text"
              className="w-full p-2 border rounded mt-1"
              value={formData.name}
              onChange={e => setFormData(prev => ({ ...prev, name: e.target.value }))}
            />
          </div>
          <div>
            <label className="text-sm text-muted-foreground">Số điện thoại</label>
            <input
              type="text"
              className="w-full p-2 border rounded mt-1"
              value={formData.phone}
              onChange={e => setFormData(prev => ({ ...prev, phone: e.target.value }))}
            />
          </div>
          <div>
            <label className="text-sm text-muted-foreground">Email</label>
            <input
              type="email"
              className="w-full p-2 border rounded mt-1"
              value={formData.email}
              onChange={e => setFormData(prev => ({ ...prev, email: e.target.value }))}
            />
          </div>
          <div>
            <label className="text-sm text-muted-foreground">Địa chỉ</label>
            <textarea
              className="w-full p-2 border rounded mt-1"
              rows={3}
              value={formData.address}
              onChange={e => setFormData(prev => ({ ...prev, address: e.target.value }))}
            />
          </div>

          <div className="flex justify-end gap-2 pt-4">
            <Button variant="outline" onClick={() => onOpenChange(false)}>
              Hủy
            </Button>
            <Button onClick={handleSubmit}>
              Thêm kho
            </Button>
          </div>
        </div>
      </DialogContent>
    </Dialog>
  );
}
