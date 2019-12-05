import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { matExpansionAnimations, MatExpansionPanelState } from '@angular/material';
import { Subscription } from 'rxjs';

/**
 *
 */
@Component({
  selector: 'menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css'],
  animations: [
    matExpansionAnimations.bodyExpansion,
    matExpansionAnimations.indicatorRotate,
  ],
})
export class MenuComponent implements OnInit
{
  /**
   *
   */
  @Input()
  public sidenav: any;

  /**
   *
   */
  @Output()
  public onToogle: EventEmitter<any> = new EventEmitter();

  /**
   *
   */
  public menuGroups: any[] = [];

  /**
   *
   */
  private userSubscription: Subscription;

  /**
   *
   */
  constructor() { }

  /**
   *
   */
  ngOnInit()
  {
    this.createMenu();
  }

  /**
   *
   */
  ngOnChanges()
  {
    this.createMenu();
  }

  /**
   *
   */
  public createMenu()
  {
    this.menuGroups = [
        {
          label: "Cadastros Básicos",
          menuList: [
            { icon: 'people', label: 'Aluno', routerlink: '/admin/aluno' },
            { icon: 'people', label: 'Professor', routerlink: '/admin/professor' }
          ],
          open: false
        },
        {
          label: "Cadastros Complexos",
          menuList: [
            { icon: 'assessment', label: 'Curso', routerlink: '/admin/curso' },
            { icon: 'assessment', label: 'Reforço', routerlink: '/admin/reforco' },
            { icon: 'toys', label: 'Matricular no Curso', routerlink: '/admin/matricula' },
            { icon: 'store', label: 'Entrar na Turma', routerlink: '/admin/turma' },
          ],
          open: false
        },
      ]

    this.removeEmptyItens();
  }

  /**
   *
   */
  removeEmptyItens()
  {
    this.menuGroups = this.menuGroups.filter((menuItem) => menuItem != null);
    this.menuGroups.forEach((menuItem) => menuItem.menuList = menuItem.menuList.filter((menuListItem) => menuListItem != null));
  }

  /**
   *
   */
  ngOnDestroy()
  {
    this.userSubscription.unsubscribe();
  }

  /** Gets the expanded state string. */
  getExpandedState(menuGroup): MatExpansionPanelState {
    return menuGroup.open ? 'expanded' : 'collapsed';
  }
}
