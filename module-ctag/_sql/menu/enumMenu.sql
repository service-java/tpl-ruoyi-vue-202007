-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('特征项枚举', '2000', '1', 'enum', 'ctag/enum/index', 1, 'C', '0', '0', 'ctag:enum:list', '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '特征项枚举菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('特征项枚举查询', @parentId, '1',  '#', '', 1,  'F', '0',  '0', 'ctag:enum:query',        '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('特征项枚举新增', @parentId, '2',  '#', '', 1,  'F', '0',  '0', 'ctag:enum:add',          '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('特征项枚举修改', @parentId, '3',  '#', '', 1,  'F', '0',  '0', 'ctag:enum:edit',         '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('特征项枚举删除', @parentId, '4',  '#', '', 1,  'F', '0',  '0', 'ctag:enum:remove',       '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('特征项枚举导出', @parentId, '5',  '#', '', 1,  'F', '0',  '0', 'ctag:enum:export',       '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');
