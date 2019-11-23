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
            { icon: 'people', label: 'Usuários', routerlink: 'usuarios'},
            { icon: 'store', label: 'Departamentos', routerlink: 'departamentos'},
            { icon: 'people', label: 'Funcionários', routerlink: 'funcionarios'},
            { icon: 'toys', label: 'Produtos', routerlink: 'produtos' }
          ],
          open: false
        },
        {
          label: "Operação",
          menuList: [
            { icon: 'store', label: 'Vendas', routerlink: 'vendas' },
            { icon: 'store', label: 'Compras', routerlink: 'compras'}
          ],
          open: false
        },

        {
          label: "Relatórios",
          menuList: [
            { icon: 'assessment', label: 'Vendas', routerlink: 'relatorio-vendas' },
            { icon: 'assessment', label: 'Compras', routerlink: 'relatorio-compras'}
          ],
          open: false
        }
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
