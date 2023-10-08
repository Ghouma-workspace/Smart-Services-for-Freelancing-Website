import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA} from '@angular/material/dialog';
import { DialogData } from 'src/app/ProjectDetail/ProjectDetail.component';


@Component({
  selector: 'app-modal',
  templateUrl: './modal.component.html',
  styleUrls: ['./modal.component.scss']
})
export class ModalComponent {
  constructor(@Inject(MAT_DIALOG_DATA) public data: DialogData) {}
  
}
